package com.example.myapplication.data.remote.network

import com.example.myapplication.data.remote.entities.response.ProductInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductInfoApiClient {
    @GET("appclienteservices/services/v7/plp/sf")
    suspend fun getProductInfo(
        @Query("page-number") pageNumber: Int,
        @Query("search-string") searchString: String,
        @Query("number-of-items-per-page") numberOfItemsPerPage: Int
    ): ProductInfoResponse
}