package com.example.myapplication.presentation.di

import com.example.myapplication.domain.repository.paged.ProductLisSource
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
        productLisSource: ProductLisSource
    ):ProductListViewModel{
        return ProductListViewModel( productLisSource)
    }
}