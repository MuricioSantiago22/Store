package com.example.myapplication.data.remote.entities.dto.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("plpResults")
    val results : PlpResults
)