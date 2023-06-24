package com.example.composeapplication.features_products.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val rate: Double,
    val count: Long

):Parcelable
