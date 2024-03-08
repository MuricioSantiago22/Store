package com.example.myapplication.data.remote.entities.dto

import com.google.gson.annotations.SerializedName

data class RecordsDto (
     @SerializedName("productDisplayName")
     var name: String? = null,
     @SerializedName("listPrice")
     var listPrice : Double? = null,
     @SerializedName("promoPrice")
     var promoPrice: Double? = null,
     @SerializedName("smImage")
     var image: String? = null,
     @SerializedName("variantsColor")
     var colors: List<ColorsDto?> = listOf()
)
