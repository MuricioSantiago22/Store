package com.example.myapplication.domain.useCase

import com.example.myapplication.domain.entities.action.Either
import com.example.myapplication.domain.repository.ProductListRepository
import javax.inject.Inject

class ProductListUseCase @Inject constructor(private val productListRepository: ProductListRepository){

    operator fun invoke(): Either {
        return productListRepository.getProductInfo()
    }
}