package com.alix01z.cryptoyar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.alix01z.cryptoyar.database.entities.MarketListEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marketListEntity: MarketListEntity?)

    @Query("SELECT * FROM table_AllMarket")
    fun getAllMarketData(): Flow<MarketListEntity?>?
}