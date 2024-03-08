package com.example.myapplication.presentation.features.productListView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.entities.action.Either
import com.example.myapplication.domain.entities.data.Records
import com.example.myapplication.domain.useCase.ProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productListUseCase: ProductListUseCase,
    private val coroutineContext: CoroutineContext
):ViewModel() {

    private val _productList:MutableLiveData<List<Records>> = MutableLiveData()
    val productList:LiveData<List<Records>> get() = _productList

    init {
        getProductList()
    }

    private fun getProductList(){
        viewModelScope.launch(coroutineContext) {
            val result = productListUseCase()
            withContext(Dispatchers.Main){
                when(result){
                    is Either.Error -> {
                        result.error
                    }
                    is Either.Success -> {
                        _productList.value = result.getData()

                    }
                }
            }
        }
    }
}
