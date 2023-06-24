package com.example.composeapplication.features_products.domain.repository

import com.example.composeapplication.features_products.domain.model.ProductList
import com.example.composeapplication.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<Resource<List<ProductList>>>
    suspend fun getProductCategories(): List<String>

}