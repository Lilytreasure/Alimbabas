package com.example.composeapplication.features_products.data.remote.dto

enum class CategoryDto(val value: String) {


    //fetch the categories directly from  the API

    Electronics("electronics"),
    Jewelery("jewelery"),
    MenSClothing("men's clothing"),
    WomenSClothing("women's clothing");

    companion object {
        public fun fromValue(value: String): CategoryDto = when (value) {
            "electronics"      -> Electronics
            "jewelery"         -> Jewelery
            "men's clothing"   -> MenSClothing
            "women's clothing" -> WomenSClothing
            else               -> throw IllegalArgumentException()
        }
    }




}