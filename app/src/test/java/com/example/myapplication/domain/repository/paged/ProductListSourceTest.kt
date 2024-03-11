package com.example.myapplication.domain.repository.paged

import androidx.paging.PagingSource
import com.example.myapplication.domain.entities.data.Colors
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.domain.repository.ProductListRepository
import org.junit.Test
import org.mockito.Mockito.mock
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.mockito.Mockito.`when`

class ProductListSourceTest {

    @Test
    fun `test load`() {
        val repository = mock(ProductListRepository::class.java)
        val source = ProductLisSource(repository)
        val pageNumber = 1
        val query = "test"
        val category = "category"
        val loadParams = PagingSource.LoadParams.Refresh(
            1,
            40,
            false)
        val response = createTestRecordsList()
        runBlocking {
            source.setQuery(query)
            source.setCategory(category)
            `when`(repository.getProductInfo(pageNumber, query, category))
                .thenReturn(response)
        }

        val result = runBlocking {
            source.load(loadParams)
        }

        assertEquals(PagingSource.LoadResult.Page(
            createTestRecordsList(),null,2
        ), result)
    }

    private fun createTestRecordsList(): List<Records> {
        return listOf(
            Records(
                "name",
                0f,
                0f,
                "image",
                listOf(
                    Colors(
                        "colorHex"
                    )
                )
            )
        )
    }
}