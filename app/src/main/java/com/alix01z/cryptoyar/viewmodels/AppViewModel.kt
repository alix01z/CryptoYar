package com.alix01z.cryptoyar.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alix01z.cryptoyar.R
import com.alix01z.cryptoyar.database.entities.MarketListEntity
import com.alix01z.cryptoyar.models.AllMarketModel
import com.alix01z.cryptoyar.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _viewPagerData = MutableLiveData<List<Int>>()
    val viewPagerData : LiveData<List<Int>> = _viewPagerData

    private val _fetchedMarketData = MutableLiveData<Response<AllMarketModel>>()
    val fetchedMarketData: LiveData<Response<AllMarketModel>> = _fetchedMarketData

    private val _errorMessages = MutableLiveData<String>()

    private val _marketDataDB = MutableLiveData<MarketListEntity>()
    val marketDataDB : LiveData<MarketListEntity> = _marketDataDB

    val errorMessages: LiveData<String> get() = _errorMessages

    init {
        getViewPagerData()
        loadDataPeriodically()
        getAllMarketDataDB()
    }

    fun getViewPagerData() : MutableLiveData<List<Int>> {
        val drawableList : List<Int> = listOf(
            R.drawable.crypto1,
            R.drawable.crypto2,
            R.drawable.crypto3
        )
        _viewPagerData.postValue(drawableList)
        return _viewPagerData
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
                    _fetchedMarketData.postValue(it)
                    if (it.isSuccessful) {
                        val marketData = it.body()
                        if (marketData != null){
                            val marketEntity = MarketListEntity(0 , marketData)
                            insertMarketDataDB(marketEntity)
                        }
//                        if (marketData != null){
//                            insertMarketDataDB(marketData)
//                        }
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

    fun insertMarketDataDB(marketModel: MarketListEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMarketData(marketModel)
        }

    }

    fun getAllMarketDataDB(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllMarketData().collect(){
                _marketDataDB.postValue(it)
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