package com.example.composeapplication.features_products.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductListDto(
@SerializedName("id")
    val id: Long,
@SerializedName("title")
    val title: String,
@SerializedName("price")
    val price: Double,
@SerializedName("description")
    val description: String,
@SerializedName("category")
    val category: String,
@SerializedName("image")
    val image: String,
@SerializedName("rating")
   val ratingDTo: RatingDTo

)
