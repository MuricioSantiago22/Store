package com.example.myapplication.presentation.features.productListView

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.domain.repository.paged.ProductLisSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val  productLisSource: ProductLisSource
):ViewModel() {

    val products : Flow<PagingData<Records>> = Pager(PagingConfig(pageSize = 40)){
            productLisSource

    }.flow
}
