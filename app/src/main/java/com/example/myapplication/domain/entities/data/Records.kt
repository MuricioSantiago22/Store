package com.example.myapplication.domain.entities.data



data class Records (
    var name: String ,
    var listPrice : Double,
    var promoPrice: Double,
    var image: String,
    var colors: List<Colors>
)
