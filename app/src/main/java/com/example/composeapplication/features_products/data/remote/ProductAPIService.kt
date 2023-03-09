package com.example.composeapplication.features_products.data.remote

import com.example.composeapplication.features_products.data.remote.dto.ProductListDto
import retrofit2.http.GET

interface ProductAPIService {

    @GET("products")
    suspend fun getProducts(): List<ProductListDto>

    @GET("products/categories")
    suspend fun getProductCategories(): List<String>

}