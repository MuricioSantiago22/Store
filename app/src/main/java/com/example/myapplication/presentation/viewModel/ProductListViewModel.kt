package com.example.myapplication.presentation.viewModel

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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val  productLisSource: ProductLisSource
):ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery

    private val _category = MutableStateFlow("")
    private val category: StateFlow<String> = _category

    val products: Flow<PagingData<Records>> = combine(
        searchQuery,
        category
    ) { query, category ->
        Pair(query, category)
    }.flatMapLatest {
        Pager(PagingConfig(pageSize = 40)) {
            productLisSource
        }.flow

    }
    fun setSearch(query: String, category: String) {
        _searchQuery.value = query
        productLisSource.setQuery(query)
        _category.value = category
        productLisSource.setCategory(category)

    }
}
