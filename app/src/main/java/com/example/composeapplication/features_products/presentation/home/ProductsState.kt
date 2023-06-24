package com.example.composeapplication.features_products.presentation.home

import com.example.composeapplication.features_products.domain.model.ProductList

data class ProductsState(
    val products: List<ProductList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)





