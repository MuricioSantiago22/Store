package com.example.myapplication.presentation.features.productListView

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.domain.repository.paged.ProductLisSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val  productLisSource: ProductLisSource
):ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery

    val products : Flow<PagingData<Records>> = searchQuery.flatMapLatest { query ->
        Pager(PagingConfig(pageSize = 40)){
            productLisSource

        }.flow
    }

    fun setQuery(query:String){
        _searchQuery.value = query
        productLisSource.setQuery(query)

    }
}
