@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication.presentation.features.productListView

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.presentation.state.ErrorItem
import com.example.myapplication.presentation.state.LoadingItem
import com.example.myapplication.presentation.state.LoadingView
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen() {
    val viewModel: ProductListViewModel = hiltViewModel()
    val topAppBarTextStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Productos",
                        textAlign = TextAlign.Center,
                        style = topAppBarTextStyle,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth()
        ) {
            SearchBarProducts()
            ProductList(products = viewModel.products, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun ProductList(products: Flow<PagingData<Records>>, modifier: Modifier = Modifier) {
    val lazyProductItems = products.collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(lazyProductItems) { product ->
                ProductItem(records = product!!)
            }
        }

        when {
            lazyProductItems.loadState.refresh is LoadState.Loading -> {
                LoadingView(modifier = Modifier.fillMaxSize())
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

@Composable
fun SearchBarProducts(){
    var query by remember{ mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange = {query= it},
        onSearch ={},
        active = active ,
        onActiveChange ={active= it}
    ) {

    }
}


