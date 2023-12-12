package com.example.cyrptocurrencyapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.domain.useCase.GetAllCoinUseCase
import com.example.cyrptocurrencyapp.utils.ResponseState
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getAllCoinUseCase: GetAllCoinUseCase
) : ViewModel() {

    private var _allCoin = MutableLiveData<MutableList<CoinDataModel>>()
    val allCoin: LiveData<MutableList<CoinDataModel>> = _allCoin

    private var _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> = _errorMessage


    fun getAllCoin(page: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            getAllCoinUseCase.invoke(page).collect {
                when (it) {
                    is ResponseState.Loading -> {

                    }

                    is ResponseState.Error -> {

                    }

                    is ResponseState.Success -> {
                        _allCoin.postValue(it.data ?: mutableListOf())
                    }
                }
            }
        }
    }
}