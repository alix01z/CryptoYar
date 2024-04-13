package com.alix01z.cryptoyar.models

import com.google.gson.annotations.SerializedName

data class ListDetails(
    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("volume24h")
    val volume24h: Double,

    @SerializedName("volume7d")
    val volume7d: Double,

    @SerializedName("volume30d")
    val volume30d: Double,

    @SerializedName("marketCap")
    val marketCap: Double,

    @SerializedName("percentChange1h")
    val percentChange1h: Double,

    @SerializedName("lastUpdated")
    val lastUpdated: String,

    @SerializedName("percentChange24h")
    val percentChange24h: Double,

    @SerializedName("percentChange7d")
    val percentChange7d: Double,

    @SerializedName("percentChange30d")
    val percentChange30d: Double,

    @SerializedName("percentChange60d")
    val percentChange60d: Double,

    @SerializedName("percentChange90d")
    val percentChange90d: Double,

    @SerializedName("fullyDilluttedMarketCap")
    val fullyDilluttedMarketCap: Double,

    @SerializedName("marketCapByTotalSupply")
    val marketCapByTotalSupply: Double,

    @SerializedName("dominance")
    val dominance: Double,

    @SerializedName("turnover")
    val turnover: Double,

    @SerializedName("ytdPriceChangePercentage")
    val ytdPriceChangePercentage: Double,

    @SerializedName("percentChange1y")
    val percentChange1y: Double
)
