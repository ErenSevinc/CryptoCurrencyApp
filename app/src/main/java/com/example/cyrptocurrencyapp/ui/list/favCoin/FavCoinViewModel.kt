package com.example.cyrptocurrencyapp.ui.list.favCoin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.domain.useCase.GetAllCoinUseCase
import com.example.cyrptocurrencyapp.utils.ResponseState
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavCoinViewModel @Inject constructor(
    private val getAllCoinUseCase: GetAllCoinUseCase
) : ViewModel() {
    private var _favCoins = MutableLiveData<MutableList<CoinDataModel>>()
    val favCoins: LiveData<MutableList<CoinDataModel>> = _favCoins

    private var _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private var _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> = _errorMessage

    val favDocument = MutableLiveData<QueryDocumentSnapshot?>(null)

    fun checkFavourite(coinList: MutableList<CoinDataModel>, result: QuerySnapshot) {
        val list = mutableListOf<CoinDataModel>()
        coinList.forEach { coin ->
            result.forEach { res ->
                if (coin.id == res.data["id"]) {
                    list.add(coin)
                }
            }
        }
        _favCoins.postValue(list)
    }
}