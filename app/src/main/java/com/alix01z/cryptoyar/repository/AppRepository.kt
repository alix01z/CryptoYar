package com.alix01z.cryptoyar.repository

import android.util.Log
import com.alix01z.cryptoyar.database.RoomDao
import com.alix01z.cryptoyar.database.entities.MarketListEntity
import com.alix01z.cryptoyar.models.AllMarketModel
import com.alix01z.cryptoyar.network.RequestAPI
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class AppRepository
@Inject constructor(private val requestAPI: RequestAPI , private val roomDao: RoomDao) {

    // >>NETWORK<< Functions
    suspend fun fetchMarketData(): Response<AllMarketModel>? {
        return try {
            val response = requestAPI.makeMarketLatestListCall()
            response
        } catch (e: Exception) {
            // Handle exceptions or errors appropriately
            Log.e("Retrofit", "Error fetching market data: ${e.message}", e)
            null
        }
    }
    // >>DATABASE<< Functions
    suspend fun insertMarketData(markeEntity: MarketListEntity){
        roomDao.insert(markeEntity)
    }

    fun getAllMarketData():Flow<MarketListEntity>{
        return roomDao.getAllMarketData()
    }


//    suspend fun fetchMarketData(): AllMarketModel? {
//        return try {
//            val response = requestAPI.makeMarketLatestListCall()
//            if (response.isSuccessful) {
//                response.body()
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            // Handle exceptions or errors appropriately
//            null
//        }
//    }
}