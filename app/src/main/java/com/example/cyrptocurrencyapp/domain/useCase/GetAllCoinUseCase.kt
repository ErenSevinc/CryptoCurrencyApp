package com.example.cyrptocurrencyapp.domain.useCase

import com.example.cyrptocurrencyapp.data.model.CoinDataModel
import com.example.cyrptocurrencyapp.data.network.ApiRepository
import com.example.cyrptocurrencyapp.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCoinUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    operator fun invoke(page: String?): Flow<ResponseState<MutableList<CoinDataModel>>> = flow {
        try {
            emit(ResponseState.Loading())
            val coins= apiRepository.getAllCoin(page).map {
                it.toCoinDataModel()
            }
            emit(ResponseState.Success(coins.toMutableList()))

        } catch (e: HttpException) {
            emit(ResponseState.Error(errorMessage = e.localizedMessage ?: "An Unexpected Error"))

        } catch (e: IOException) {
            emit(ResponseState.Error(errorMessage = "Error"))
        }
    }
}