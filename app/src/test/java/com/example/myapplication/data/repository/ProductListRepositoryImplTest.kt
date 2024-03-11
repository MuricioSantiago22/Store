package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.entities.dto.ColorsDto
import com.example.myapplication.data.remote.entities.dto.RecordsDto
import com.example.myapplication.data.remote.entities.response.PlpResults
import com.example.myapplication.data.remote.entities.response.ProductInfoResponse
import com.example.myapplication.data.remote.mapper.toDomain
import com.example.myapplication.data.remote.network.ProductInfoApiClient
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ProductListRepositoryImplTest {


    @Test
    fun `test getProductInfo`() {

        val apiClient = mock(ProductInfoApiClient::class.java)

        val repository = ProductListRepositoryImpl(apiClient)

        val pageNumber = 1
        val searchString = "test"
        val sortOption = "name"
        val response = createTestProductInfoResponse()

        runBlocking {
            `when`(apiClient.getProductInfo(
                pageNumber,
                searchString,
                sortOption,
                40
            ))
                .thenReturn(response)
        }


        val result = runBlocking {
            repository.getProductInfo(pageNumber, searchString, sortOption)
        }

        assertEquals(response.results.recordsDto.map { it.toDomain() }, result)
    }

    private fun createTestProductInfoResponse(): ProductInfoResponse {
        val recordsDtoList = listOf(
            RecordsDto(
                "name",
                0f,
                0f,
                "image",
                listOf(
                    ColorsDto(
                        "colorHex"
                    )
                )
            )
        )
        val plpResults = PlpResults(recordsDtoList)
        return ProductInfoResponse(plpResults)
    }
}