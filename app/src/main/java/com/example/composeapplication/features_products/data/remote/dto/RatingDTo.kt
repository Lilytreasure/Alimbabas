package com.example.composeapplication.features_products.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RatingDTo(
@SerializedName("rate")
    val rate: Double,
@SerializedName("count")
    val count: Long
)
