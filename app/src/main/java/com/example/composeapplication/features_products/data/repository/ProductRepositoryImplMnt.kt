package com.example.composeapplication.features_products.data.repository

import com.example.composeapplication.features_products.data.remote.ProductAPIService
import com.example.composeapplication.features_products.data.remote.mapper.toDomain
import com.example.composeapplication.features_products.domain.model.ProductList
import com.example.composeapplication.features_products.domain.repository.ProductsRepository
import com.example.composeapplication.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
class ProductRepositoryImplMnt(private  val productAPIService: ProductAPIService):ProductsRepository {
    override suspend fun getProducts(): Flow<Resource<List<ProductList>>> = flow {

        emit(Resource.Loading())
        try {

            val response=productAPIService.getProducts()
            emit(Resource.Success(response.map { it.toDomain() }))

        }catch (exception: IOException){
            emit(Resource.Error("Server unreachable check internet  connection"))

        }catch (exception: HttpException){
            emit(Resource.Error("something went wrong"))
        }
    }
    //will fetch the product categories from the user input  on the screen
    override suspend fun getProductCategories(): List<String> {
        return productAPIService.getProductCategories()
    }

}