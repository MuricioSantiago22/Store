package com.example.myapplication.presentation.di

import com.example.myapplication.domain.useCase.ProductListUseCase
import com.example.myapplication.presentation.di.CoroutineScopeModule.provideIOCoroutineContext
import com.example.myapplication.presentation.features.productListView.ProductListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    fun provideProductListViewModel(
        getProductsListUseCase:ProductListUseCase
    ):ProductListViewModel{
        return ProductListViewModel(getProductsListUseCase, provideIOCoroutineContext())
    }
}