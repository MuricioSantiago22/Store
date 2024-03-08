package com.example.myapplication.data.remote.entities.dto

import com.google.gson.annotations.SerializedName

data class RecordsDto (
     @SerializedName("productDisplayName")
     var name: String,
     @SerializedName("listPrice")
     var listPrice : Int,
     @SerializedName("promoPrice")
     var promoPrice: Int,
     @SerializedName("smImage")
     var image: String,
     @SerializedName("variantsColor")
     var colors: List<ColorsDto>
)
