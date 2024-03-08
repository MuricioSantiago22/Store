package com.example.myapplication.domain.repository

import com.example.myapplication.domain.entities.action.Either

interface ProductListRepository {
     fun getProductInfo(): Either
}