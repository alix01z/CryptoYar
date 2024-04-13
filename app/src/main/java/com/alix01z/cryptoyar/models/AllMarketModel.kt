package com.alix01z.cryptoyar.models

import com.google.gson.annotations.SerializedName

data class AllMarketModel(
    @SerializedName("data")
    val data: RootData,

    @SerializedName("status")
    val listStatus: ListStatus
) {
    data class ListStatus(
        @SerializedName("timestamp")
        val timestamp: String,

        @SerializedName("error_code")
        val errorCode: String,

        @SerializedName("error_message")
        val errorMessage: String,

        @SerializedName("elapsed")
        val elapsed: String,

        @SerializedName("credit_count")
        val creditCount: Int
    )

    data class RootData(
        @SerializedName("cryptoCurrencyList")
        val cryptoCurrencyList: List<DataItem>,

        @SerializedName("totalCount")
        val totalCount: String
    )
}
