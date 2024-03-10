package com.example.myapplication.domain.repository.paged

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.domain.repository.ProductListRepository
import javax.inject.Inject

class ProductLisSource @Inject constructor(
    private val productListRepository: ProductListRepository
): PagingSource<Int, Records>() {

    private var currentQuery: String? = null

    fun setQuery(query: String) {
        currentQuery = query
    }
    override fun getRefreshKey(state: PagingState<Int, Records>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Records> {
       return try {

           val pageNumber = params.key ?: 1
           val response = productListRepository.getProductInfo(pageNumber, currentQuery?:"")
               LoadResult.Page(
                   data = response,
                   prevKey = if (pageNumber ==1) null else pageNumber -1,
                   nextKey = if (response.isEmpty()) null else pageNumber + 1
               )
             }catch (e: Exception) {
           LoadResult.Error(e)
       }
    }

}