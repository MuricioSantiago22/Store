@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.presentation.features.productListView

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.presentation.features.stateView.ErrorItem
import com.example.myapplication.presentation.features.stateView.LoadingItem
import com.example.myapplication.presentation.features.stateView.LoadingView
import com.example.myapplication.presentation.viewModel.ProductListViewModel
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen() {
    val viewModel: ProductListViewModel = hiltViewModel()
    val categoriesMap = mapOf(
        "sortPrice|0" to "Ordenar por Menor Precio",
        "sortPrice|1" to "Ordenar por Mayor Precio",
        "Relevancia|0" to "Ordenar por Relevancia",
        "newArrival|1" to "Ordenar por Lo Más Nuevo",
        "rating|1" to "Ordenar por Calificaciones"
    )

    var showMenu by remember { mutableStateOf(false) }
    val topAppBarTextStyle = MaterialTheme.typography.headlineSmall
        .copy(fontWeight = FontWeight.Bold)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Productos",
                        textAlign = TextAlign.Center,
                        style = topAppBarTextStyle,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Magenta,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = { showMenu = !showMenu },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = "Filter"
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        categoriesMap.forEach { (originalCategory, displayedCategory) ->
                            DropdownMenuItem(
                                text = {
                                    Text("$displayedCategory")
                                },
                                onClick = {
                                    viewModel.setSearch("", originalCategory)
                                    showMenu = !showMenu
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            SearchBarProducts(viewModel)
            ProductList(products = viewModel.products, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun ProductList(products: Flow<PagingData<Records>>, modifier: Modifier = Modifier) {
    val lazyProductItems = products.collectAsLazyPagingItems()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(lazyProductItems) { product ->
                ProductItem(
                    records = product
                    ?: Records(
                        name = "",
                        listPrice = 0f,
                        promoPrice = 0f,
                        image = "",
                        variantsColor = listOf()
                    )
                )
            }
        }
        when {
            lazyProductItems.loadState.refresh is LoadState.Loading -> {
                LoadingView(
                    modifier = Modifier.fillMaxSize()
                )
            }
            lazyProductItems.loadState.append is LoadState.Loading -> {
                LoadingItem()
            }
            lazyProductItems.loadState.refresh is LoadState.Error -> {
                val e = lazyProductItems.loadState.refresh as LoadState.Error
                ErrorItem(
                    message = e.error.localizedMessage ?: "",
                    modifier = Modifier.fillMaxSize(),
                    onClickRetry = { lazyProductItems.retry() }
                )
            }
            lazyProductItems.loadState.append is LoadState.Error -> {
                val e = lazyProductItems.loadState.append as LoadState.Error
                ErrorItem(
                    message = e.error.localizedMessage ?: "",
                    modifier = Modifier.fillMaxSize(),
                    onClickRetry = { lazyProductItems.retry() }
                )
            }
        }
    }
}



