package com.example.composeapplication.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.data.ProductList
import com.example.composeapplication.network.productsListApi
import kotlinx.coroutines.launch


class ProductsViewModel: ViewModel() {


    //implement the products view model
    var productsListResponse: List<ProductList>  by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")



    fun getProductList(){

        viewModelScope.launch {

            val apiservice=productsListApi.getInstance()

            try {

                //get the  list of the products

                val productList=apiservice.getProductsList()

                productsListResponse=productList


            }catch (e: Exception){

                //when  the operation  fails

                errorMessage=e.message.toString()



            }



        }



    }



}