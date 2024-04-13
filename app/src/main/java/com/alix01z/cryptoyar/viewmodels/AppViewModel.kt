package com.alix01z.cryptoyar.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.models.AllMarketModel
import com.alix01z.cryptoyar.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AppViewModel
@Inject constructor(
    private val repository: AppRepository ,
    private val application: Application
): ViewModel() {

    private val vPMutableData : MutableLiveData<List<Int>> = MutableLiveData()
    val vPLiveData : LiveData<List<Int>> = vPMutableData

    private val marketDataMutableLD = MutableLiveData<Response<AllMarketModel>>()
    val marketDataLD: LiveData<Response<AllMarketModel>> = marketDataMutableLD

    private val _errorMessages = MutableLiveData<String>()
    val errorMessages: LiveData<String> get() = _errorMessages

    init {
        getViewPagerData()
        loadDataPeriodically()
    }

    fun getViewPagerData() : MutableLiveData<List<Int>> {
        val drawableList : List<Int> = listOf(
            R.drawable.crypto1,
            R.drawable.crypto2,
            R.drawable.crypto3
        )
        vPMutableData.postValue(drawableList)
        return vPMutableData
    }

    private fun loadDataPeriodically(){
        viewModelScope.launch {
            while (isActive){
                loadMarketData()
                delay(61000)
            }
        }
    }

    private fun loadMarketData() {
        viewModelScope.launch {
            try {
                val response = repository.fetchMarketData()
                response?.let {
                    marketDataMutableLD.postValue(it)
                    if (it.isSuccessful) {
                        val marketData = it.body()
                        // Use marketData here (parsed data)
                    } else {
                        // Handle network error
                        _errorMessages.postValue("Request failed: ${it.code()}")
                    }
                }
            } catch (e: Exception) {
                // Handle exception
                Log.e("ViewModel", "Error loading market data: ${e.message}", e)
                _errorMessages.postValue("Error loading market data")
            }
        }
    }
//    private fun loadMarketData() {
//        viewModelScope.launch {
//            val data = repository.fetchMarketData()
//            data?.let {
//                marketDataMutableLD.postValue(it)
//            }
//        }
//    }

}