package com.alix01z.cryptoyar.repository

import com.alix01z.cryptoyar.models.AllMarketModel
import com.alix01z.cryptoyar.network.RequestAPI
import retrofit2.Response
import javax.inject.Inject

class AppRepository
@Inject constructor(private val requestAPI: RequestAPI) {

    suspend fun fetchMarketData(): Response<AllMarketModel>? {
        return try {
            val response = requestAPI.makeMarketLatestListCall()
            response
        } catch (e: Exception) {
            // Handle exceptions or errors appropriately
            null
        }
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