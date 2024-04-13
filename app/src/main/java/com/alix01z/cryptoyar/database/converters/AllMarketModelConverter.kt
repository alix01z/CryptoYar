package com.alix01z.cryptoyar.database.converters

import androidx.room.TypeConverter
import com.alix01z.cryptoyar.models.AllMarketModel
import com.google.gson.Gson

object AllMarketModelConverter {
    private val gson by lazy { Gson() } // Lazy initialization of Gson

    @TypeConverter
    fun toJson(allMarketModel: AllMarketModel?): String? = allMarketModel?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJson(json: String?): AllMarketModel? = json?.let { gson.fromJson(it, AllMarketModel::class.java) }
}