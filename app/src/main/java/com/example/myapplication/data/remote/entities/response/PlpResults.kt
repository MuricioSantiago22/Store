package com.example.myapplication.data.remote.entities.response

import com.example.myapplication.data.remote.entities.dto.RecordsDto
import com.google.gson.annotations.SerializedName

data class PlpResults (
    @SerializedName("records")
    val recordsDto: List<RecordsDto>
)
