package com.example.composeapplication.data

enum class Category(val value: String) {

    Electronics("electronics"),
    Jewelery("jewelery"),
    MenSClothing("men's clothing"),
    WomenSClothing("women's clothing");

    companion object {
        public fun fromValue(value: String): Category = when (value) {
            "electronics"      -> Electronics
            "jewelery"         -> Jewelery
            "men's clothing"   -> MenSClothing
            "women's clothing" -> WomenSClothing
            else               -> throw IllegalArgumentException()
        }
    }




}