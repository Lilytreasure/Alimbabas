package com.example.composeapplication.features_products.data.remote.dto

import com.example.composeapplication.features_products.domain.model.Category
import com.example.composeapplication.features_products.domain.model.Rating
import com.google.gson.annotations.SerializedName

data class ProductListDto(
    //Serialize  the data coming from  the network
@SerializedName("id")
    val id: Long,
@SerializedName("title")
    val title: String,
@SerializedName("price")
    val price: Double,
@SerializedName("description")
    val description: String,
@SerializedName("category")
//users will choose  the category they wish to  navigate to in the application layout
    val category: String,
@SerializedName("image")
    val image: String,
@SerializedName("rating")
   val ratingDTo: RatingDTo

)
