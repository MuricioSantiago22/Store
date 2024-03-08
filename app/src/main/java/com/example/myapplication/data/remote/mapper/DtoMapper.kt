package com.example.myapplication.data.remote.mapper

import com.example.myapplication.data.remote.entities.dto.ColorsDto
import com.example.myapplication.data.remote.entities.dto.RecordsDto
import com.example.myapplication.domain.entities.data.Colors
import com.example.myapplication.domain.entities.data.Records

fun RecordsDto.toDomain() = Records(
    name = this.name,
    listPrice= this.listPrice,
    promoPrice= this.promoPrice,
    image= this.image,
    colors= this.colors.map { it.toDomain()}
)

fun ColorsDto.toDomain()= Colors(
    colorHex= this.colorHex
)