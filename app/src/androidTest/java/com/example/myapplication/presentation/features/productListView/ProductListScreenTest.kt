package com.example.myapplication.presentation.features.productListView

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.domain.entities.data.Records
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class ProductListScreenTest {
    @RunWith(AndroidJUnit4::class)
    class ProductListScreenTest {

        @get:Rule
        val composeTestRule = createComposeRule()

        @Test
        fun productListScreen_RenderedCorrectly() {
            val productsFlow = flowOf(PagingData.from(fakeProductsList))

            composeTestRule.setContent {
                ProductList(products = productsFlow)
            }
            fakeProductsList.forEach { product ->
                composeTestRule.onNodeWithText(product.name).assertIsDisplayed()
            }
        }



        @Test
        fun productListScreen_ClickRetryButton_RetriesLoading() {
            val productsFlow = flowOf(PagingData.from(fakeProductsList))
            val errorMessage = "Error loading products"

            composeTestRule.setContent {
                ProductList(products = productsFlow)
            }

            composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
            composeTestRule.onNodeWithText("Retry").performClick()
            composeTestRule.onNodeWithText("Loading...").assertIsDisplayed()
        }

        private val fakeProductsList = listOf(
            Records(
                name = "Product 1",
                listPrice = 10.0f,
                promoPrice = 8.0f,
                image = "",
                variantsColor = listOf()
            ),
            Records(
                name = "Product 2",
                listPrice = 20.0f,
                promoPrice = 18.0f,
                image = "",
                variantsColor = listOf()
            ),
            Records(
                name = "Product 3",
                listPrice = 15.0f,
                promoPrice = 12.0f,
                image = "",
                variantsColor = listOf()
            )
        )
    }
}