//package com.example.composeapplication.api
//
//import com.example.composeapplication.data.ProductList
//import retrofit2.http.GET
//
//interface ProductListApi {
//
//    //add the Base Url  and the get  query
//
//    companion object{
//        const val BASE_URL="https://fakestoreapi.com/"
//    }
//
//    //Get request
//
//    @GET("products")
//    suspend fun getProductsList():List<ProductList>
//
//
//
//}