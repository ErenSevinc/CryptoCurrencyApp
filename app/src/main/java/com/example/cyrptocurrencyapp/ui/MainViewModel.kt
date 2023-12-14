package com.example.cyrptocurrencyapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.domain.useCase.GetAllCoinUseCase
import com.example.cyrptocurrencyapp.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllCoinUseCase: GetAllCoinUseCase
) : ViewModel() {

    private val _title = MutableLiveData("Toolbar")
    val title: LiveData<String> = _title

    private val _isToolbarVisible = MutableLiveData(true)
    val isToolbarVisible: LiveData<Boolean> = _isToolbarVisible

    private val _isToolbarNavIconVisibility = MutableLiveData(false)
    val isToolbarNavIconVisibility: LiveData<Boolean> = _isToolbarNavIconVisibility

    fun setToolbarTitle(text: String) {
        _title.value = text
    }

    fun setToolbarVisibility(isVisible: Boolean) {
        _isToolbarVisible.value = isVisible
    }

    fun setBackIconVisibility(isVisible: Boolean) {
        _isToolbarNavIconVisibility.value = isVisible
    }


}