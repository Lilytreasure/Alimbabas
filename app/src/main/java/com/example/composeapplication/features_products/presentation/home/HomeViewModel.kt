package com.example.composeapplication.features_products.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.core.utils.Resource
import com.example.composeapplication.core.utils.UiEvents
import com.example.composeapplication.features_products.domain.use_case.GetCategoriesUseCase
import com.example.composeapplication.features_products.domain.use_case.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase

)
    : ViewModel() {
    //This view model will be used to render items in the home view model
        //view data logic
    //The underscore  is used as an identifier  to differentiate  between the variables

        private val _selectedCategory= mutableStateOf("All")
         val selectedCategory: State<String> = _selectedCategory

         fun setCategory(value: String) {
        _selectedCategory.value = value
        }

    private val _productsState = mutableStateOf(productsState())
    val productsState: State<productsState> = _productsState

    private val _categoriesState = mutableStateOf(emptyList<String>())
    val categoriesState: State<List<String>> = _categoriesState

    private val _searchTerm = mutableStateOf("")
    val searchTerm: State<String> = _searchTerm

    fun setSearchTerm(term: String) {
        _searchTerm.value = term
    }

    private val _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow: SharedFlow<UiEvents> = _eventFlow.asSharedFlow()

    init {
        getProducts(selectedCategory.value)
        getCategories()

    }


    private fun getCategories() {
        viewModelScope.launch {
            _categoriesState.value = getCategoriesUseCase()
        }
    }


    fun getProducts(category: String) {
        viewModelScope.launch {
            getProductsUseCase().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        if (category == "All") {
                            _productsState.value = productsState.value.copy(
                                products = result.data ?: emptyList(),
                                isLoading = false
                            )
                        } else {
                            _productsState.value = productsState.value.copy(
                                products = result.data?.filter { it.category == category }
                                    ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _productsState.value = productsState.value.copy(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _productsState.value = productsState.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                        _eventFlow.emit(
                            UiEvents.SnackbarEvent(
                                message = result.message ?: "Unknown error occurred!"

                            )
                        )
                    }
                }
            }
        }
    }



}