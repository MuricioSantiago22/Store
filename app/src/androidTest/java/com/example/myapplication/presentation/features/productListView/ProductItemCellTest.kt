package com.example.myapplication.presentation.features.productListView

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.domain.entities.data.Colors
import com.example.myapplication.domain.entities.data.Records
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class ProductItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun productItemDisplaysCorrectly() {
        val records = Records(
            image = "product_image_url",
            name = "Product Name",
            listPrice = 100.0f,
            promoPrice = 80.0f,
            variantsColor = listOf(
                Colors(colorHex = "#FF0000"),
                Colors(colorHex = "#00FF00")
            )
        )
        composeTestRule.setContent {
            ProductItem(records = records)
        }
        composeTestRule.onNodeWithText("Product Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("$80.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("$100.0").assertIsDisplayed()
    }

}