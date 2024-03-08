package com.example.myapplication.data.remote.entities.dto

import com.google.gson.annotations.SerializedName

data class ColorsDto(
    @SerializedName("colorName")
    var colorName :String,
    @SerializedName("colorHex")
    var colorHex : String
)
