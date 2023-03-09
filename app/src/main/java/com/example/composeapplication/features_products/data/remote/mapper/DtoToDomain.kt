package com.example.composeapplication.features_products.data.remote.mapper

import com.example.composeapplication.features_products.data.remote.dto.ProductListDto
import com.example.composeapplication.features_products.data.remote.dto.RatingDTo
import com.example.composeapplication.features_products.domain.model.ProductList
import com.example.composeapplication.features_products.domain.model.Rating

internal  fun ProductListDto.toDomain():ProductList{
    //map the products to domain
    //The toDomain is use as a label to show  where the items are being mapped to


    return ProductList(

        category =category,
        description = description,
        id = id,
        image = image,
        price = price,
        rating = ratingDTo.toDomain(),
        title = title

    )

}

internal fun RatingDTo.toDomain(): Rating {
    return Rating(
        count = count,
        rate = rate
    )
}