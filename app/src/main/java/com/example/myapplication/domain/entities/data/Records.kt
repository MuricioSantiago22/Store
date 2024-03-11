package com.example.myapplication.domain.entities.data

data class Records (
    var name: String ,
    var listPrice : Float,
    var promoPrice: Float,
    var image: String,
    var variantsColor: List<Colors>
)
