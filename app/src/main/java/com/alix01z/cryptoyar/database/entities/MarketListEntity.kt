package com.alix01z.cryptoyar.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alix01z.cryptoyar.models.AllMarketModel

@Entity(tableName = "table_AllMarket")
data class MarketListEntity(
    @PrimaryKey
    @ColumnInfo("market_id")
    val uid: Int,

    @ColumnInfo("AllMarket")
    val allMarketModel: AllMarketModel
)
