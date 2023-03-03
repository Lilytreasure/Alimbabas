package com.example.composeapplication.network

import com.example.composeapplication.data.ProductList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface productsListApi {

    @GET("products")
    suspend fun getProductsList():List<ProductList>


    companion object{

        var apiservice: productsListApi?=null


        fun getInstance():productsListApi{

            if (apiservice==null){

                apiservice=Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(productsListApi::class.java)

            }

            return apiservice!!

        }


    }




}