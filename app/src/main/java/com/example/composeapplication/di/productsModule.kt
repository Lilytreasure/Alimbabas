package com.example.composeapplication.di

import com.example.composeapplication.features_products.data.remote.ProductAPIService
import com.example.composeapplication.features_products.data.repository.ProductRepositoryImplMnt
import com.example.composeapplication.features_products.domain.repository.ProductsRepository
import com.example.composeapplication.core.utils.Constants
import com.example.composeapplication.features_products.domain.use_case.GetCategoriesUseCase
import com.example.composeapplication.features_products.domain.use_case.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object productsModule {

    //use dependency injection  to add  the  relevant requirements
    //This will be used to fetch data from  the Api


    @Provides
    @Singleton
    fun provideProductsApiService(): ProductAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        productsApiService: ProductAPIService
    ): ProductsRepository {
        return ProductRepositoryImplMnt(
            productsApiService
        )
    }



    //add  The products use case

    @Provides
    @Singleton
    fun provideGetProductsUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    @Provides
    @Singleton
    fun provideGetCategoriesUseCase(productsRepository: ProductsRepository): GetCategoriesUseCase {
        return GetCategoriesUseCase(productsRepository)
    }








}