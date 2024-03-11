package com.example.myapplication.domain.repository
import com.example.myapplication.domain.entities.data.Records

interface ProductListRepository {
    suspend  fun getProductInfo(
        pageNumber:Int, searchString: String, sortOption: String
    ):List<Records>
}