package com.example.composeapplication.features_products.domain.use_case

import com.example.composeapplication.features_products.domain.repository.ProductsRepository

class GetCategoriesUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): List<String> {
        val categories = productsRepository.getProductCategories()
        return listOf("All") + categories
    }
}