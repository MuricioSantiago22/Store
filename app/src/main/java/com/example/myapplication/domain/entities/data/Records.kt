package com.example.myapplication.domain.entities.data



data class Records (
    var name: String ,
    var listPrice : Int,
    var promoPrice: Int,
    var image: String,
    var colors: List<Colors>
)
