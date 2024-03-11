package com.example.myapplication.data.remote.network

import com.example.myapplication.data.remote.entities.dto.ColorsDto
import com.example.myapplication.data.remote.entities.dto.RecordsDto
import com.example.myapplication.data.remote.entities.response.PlpResults
import com.example.myapplication.data.remote.entities.response.ProductInfoResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ProductInfoApiClientTest {

    @Test
    fun `test getProductInfo`() {
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
        ))
        val plpResults = PlpResults(recordsDtoList)
        val apiClient = mock(ProductInfoApiClient::class.java)
        val pageNumber = 1
        val searchString = "test"
        val sortOption = "name"
        val numberOfItemsPerPage = 10
        val response = ProductInfoResponse(plpResults)

        runBlocking {
            `when`(
                apiClient.getProductInfo(
                pageNumber,
                searchString,
                sortOption,
                numberOfItemsPerPage
            ))
                .thenReturn(response)
        }

        val result = runBlocking {
            apiClient.getProductInfo(
                pageNumber,
                searchString,
                sortOption,
                numberOfItemsPerPage
            )
        }

        assertEquals(response, result)
    }
}