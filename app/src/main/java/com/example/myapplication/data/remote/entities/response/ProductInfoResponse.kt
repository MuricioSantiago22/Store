package com.example.myapplication.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class ProductInfoResponse(
    @SerializedName("plpResults")
    val results : PlpResults
)