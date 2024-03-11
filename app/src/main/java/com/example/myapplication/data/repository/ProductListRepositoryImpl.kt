package com.example.myapplication.data.repository
import com.example.myapplication.data.remote.mapper.toDomain
import com.example.myapplication.data.remote.network.ProductInfoApiClient
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.domain.repository.ProductListRepository
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val client: ProductInfoApiClient
):ProductListRepository {

    override suspend  fun getProductInfo(pageNumber: Int, searchString: String,sortOption: String): List<Records> =
        client.getProductInfo(
            pageNumber,
            searchString,
            sortOption,
            40
        ).results.recordsDto.map {
            it.toDomain()
        }

}