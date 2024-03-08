package com.example.myapplication.presentation.features.productListView

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(){
    val viewModel:ProductListViewModel = hiltViewModel()
    val productList = viewModel.productList.observeAsState(initial = emptyList())

    MyApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title ={
                        Text("Productos")
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.DarkGray,
                        titleContentColor = Color.White
                    )
                )
            },
        ) { padding ->
           LazyColumn(
               modifier = Modifier.padding(padding)
           ){
               items(productList.value){ productList ->
                   ProductListCell(productList = productList)
               }
           }

        }
    }

}