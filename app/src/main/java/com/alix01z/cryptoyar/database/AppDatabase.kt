package com.alix01z.cryptoyar.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alix01z.cryptoyar.database.converters.AllMarketModelConverter
import com.alix01z.cryptoyar.database.entities.MarketListEntity

@TypeConverters(AllMarketModelConverter::class)
@Database(entities = [MarketListEntity::class] , version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun roomDao(): RoomDao
}