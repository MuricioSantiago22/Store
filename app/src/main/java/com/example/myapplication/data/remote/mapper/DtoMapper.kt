package com.example.myapplication.data.remote.mapper

import com.example.myapplication.data.remote.entities.dto.ColorsDto
import com.example.myapplication.data.remote.entities.dto.RecordsDto
import com.example.myapplication.domain.entities.data.Colors
import com.example.myapplication.domain.entities.data.Records

fun RecordsDto.toDomain() = Records(
    name = this.name ?: "",
    listPrice= this.listPrice ?: 0f,
    promoPrice= this.promoPrice ?:0f,
    image= this.image ?: "",
    variantsColor= this.variantsColor.map {
        it?.toDomain()?:Colors("")
    }
)

fun ColorsDto.toDomain()= Colors(
    colorHex= this.colorHex ?: ""
)