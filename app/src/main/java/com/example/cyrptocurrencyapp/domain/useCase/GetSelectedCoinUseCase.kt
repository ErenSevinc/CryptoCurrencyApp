package com.example.cyrptocurrencyapp.domain.useCase

import com.example.cyrptocurrencyapp.data.model.CoinDetailDataModel
import com.example.cyrptocurrencyapp.data.network.ApiRepository
import com.example.cyrptocurrencyapp.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSelectedCoinUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    operator fun invoke(id: String?): Flow<ResponseState<CoinDetailDataModel>> = flow {
        try {
            emit(ResponseState.Loading())
            val coinDetail = apiRepository.getSelectedCoin(id).toCoinDetail()
            emit(ResponseState.Success(coinDetail))

        } catch (e: HttpException) {
            emit(ResponseState.Error(errorMessage = e.localizedMessage ?: "An Unexpected Error"))

        } catch (e: IOException) {
            emit(ResponseState.Error(errorMessage = "Error"))
        }
    }
}