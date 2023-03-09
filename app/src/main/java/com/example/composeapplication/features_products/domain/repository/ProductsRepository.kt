package com.example.composeapplication.features_products.domain.repository

import com.example.composeapplication.features_products.domain.model.ProductList
import com.example.composeapplication.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    //fetch  data from the Api
    //after fetching data from the api the data  is then cached in the local storage
    //Flow will keep emmiting data checking for updates
    suspend fun getProducts(): Flow<Resource<List<ProductList>>>
    suspend fun getProductCategories(): List<String>

}