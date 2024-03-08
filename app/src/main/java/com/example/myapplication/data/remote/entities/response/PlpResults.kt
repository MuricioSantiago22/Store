package com.example.myapplication.data.remote.entities.dto.response

import com.example.myapplication.data.remote.entities.dto.RecordsDto
import com.google.gson.annotations.SerializedName

data class PlpResults (
    @SerializedName("recordsDto")
    val recordsDto: RecordsDto
)
