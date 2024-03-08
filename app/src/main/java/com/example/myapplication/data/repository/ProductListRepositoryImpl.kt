package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.mapper.toDomain
import com.example.myapplication.data.remote.network.ProductInfoApiClient
import com.example.myapplication.domain.entities.action.Either
import com.example.myapplication.domain.entities.action.error.ErrorEntity
import com.example.myapplication.domain.repository.ProductListRepository
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(private val client: ProductInfoApiClient):ProductListRepository {
    override  fun getProductInfo(): Either =try {
        val response = client.getProductInfo(1,40).execute()
        if (response.isSuccessful){
            response.body()?.let { body->
                Either.Success(body.results.recordsDto.map { it.toDomain() })
            }?: Either.Error(ErrorEntity.EmptyResponseError)
        }else {
            Either.Error(ErrorEntity.NetworkError(response.code()))
        }
    }catch (e: Exception) {
        Either.Error(ErrorEntity.UnknownError(e))
    }

}