package com.example.myapplication.presentation.features.productListView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.domain.entities.data.Records

@Composable
fun ProductItem(records: Records){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment =  Alignment.CenterVertically
    ) {
        AsyncImage(
            model = records.image,
            "Product image",
            modifier = Modifier
                .size(200.dp)
                .background(Color.White.copy(alpha = 0.1f))
                .padding(8.dp)
        )
        Spacer(
            modifier = Modifier
                .padding(4.dp)
        )
        Column {
            records.name.let {
                Text(
                    it,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            records.listPrice.let { listPrice ->
                records.promoPrice.let { promoPrice ->
                    if (promoPrice == listPrice) {
                        Text(
                            text = "$$promoPrice".toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.Red
                        )
                    } else {
                        val textWithStrikeThrough = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Medium
                                )
                            ) {
                                append("$$listPrice")
                            }
                        }
                        Text(
                            text = textWithStrikeThrough,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "$$promoPrice".toString(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.Red
                        )
                    }
                    val colorsHex = records.variantsColor.mapNotNull { color ->
                        if (!color.colorHex.isNullOrEmpty()) {
                            color.colorHex
                        } else {
                            null
                        }
                    }

                    Row {
                        for (colorHex in colorsHex) {
                            val color = Color(android.graphics.Color.parseColor(colorHex))
                            Box(modifier = Modifier.padding(1.dp)) {
                                Box(modifier = Modifier
                                    .size(15.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}