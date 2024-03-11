package com.example.myapplication.data.di

import com.example.myapplication.data.repository.ProductListRepositoryImpl
import com.example.myapplication.domain.repository.ProductListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideProductInfoRepository (
        productListRepositoryImpl: ProductListRepositoryImpl
    ):ProductListRepository
}