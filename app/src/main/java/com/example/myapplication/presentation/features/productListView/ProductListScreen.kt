package com.example.myapplication.presentation.features.productListView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.myapplication.domain.entities.data.Colors
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.presentation.state.ErrorItem
import com.example.myapplication.presentation.state.LoadingItem
import com.example.myapplication.presentation.state.LoadingView
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(){
    val viewModel:ProductListViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Productos") }
            )
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
        {
            ProductList(products = viewModel.products)
        }
    }
}


@Composable
fun ProductList(products: Flow<PagingData<Records>>){
    val lazyProductItems= products.collectAsLazyPagingItems()
    LazyColumn{
        items(lazyProductItems){ product ->
            ProductItem(records = product!!)

        }
    }
    lazyProductItems.apply {
        when{
            loadState.refresh is LoadState.Loading ->{
                LoadingView(modifier = Modifier.fillMaxSize())
            }
            loadState.append is LoadState.Loading ->{
                LoadingItem()
            }
            loadState.refresh is LoadState.Error ->{

                val e = lazyProductItems.loadState.refresh as LoadState.Error
                ErrorItem(message = e.error.localizedMessage?:"",
                    modifier = Modifier.fillMaxSize(),
                    onClickRetry = {retry()}
                )

            }
            loadState.append is LoadState.Error ->{
                val e = lazyProductItems.loadState.refresh as LoadState.Error
                ErrorItem(message = e.error.localizedMessage?:"",
                    modifier = Modifier.fillMaxSize(),
                    onClickRetry = {retry()}
                )
            }
        }
    }
}

