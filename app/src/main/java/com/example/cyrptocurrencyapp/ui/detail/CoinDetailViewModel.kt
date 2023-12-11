package com.example.cyrptocurrencyapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.data.model.CoinDetailDataModel
import com.example.cyrptocurrencyapp.domain.useCase.GetAllCoinUseCase
import com.example.cyrptocurrencyapp.domain.useCase.GetSelectedCoinUseCase
import com.example.cyrptocurrencyapp.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getSelectedCoinUseCase: GetSelectedCoinUseCase
) : ViewModel() {

    private var _selectedCoin = MutableLiveData<CoinDetailDataModel?>()
    val selectedCoin: LiveData<CoinDetailDataModel?> = _selectedCoin

    private var _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> = _errorMessage


    fun getSelectedCoin(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSelectedCoinUseCase.invoke(id).collect {
                when (it) {
                    is ResponseState.Loading -> {

                    }

                    is ResponseState.Error -> {

                    }

                    is ResponseState.Success -> {
                        _selectedCoin.postValue(it.data)
                    }
                }
            }
        }
    }
}