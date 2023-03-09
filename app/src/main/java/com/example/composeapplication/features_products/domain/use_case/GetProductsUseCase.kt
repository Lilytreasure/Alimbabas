package com.example.composeapplication.features_products.domain.use_case

import com.example.composeapplication.features_products.domain.model.ProductList
import com.example.composeapplication.features_products.domain.repository.ProductsRepository
import com.example.composeapplication.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<ProductList>>> {
        return productsRepository.getProducts()
    }
}