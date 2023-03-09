package com.example.composeapplication.features_products.presentation.home

import com.example.composeapplication.features_products.domain.model.ProductList

data class productsState(

    //this will be used to handle  the exceptions that occurs when loading the products

    val products: List<ProductList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null

)





