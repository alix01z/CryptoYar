package com.alix01z.cryptoyar.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alix01z.cryptoyar.database.converters.AllMarketModelConverter
import com.alix01z.cryptoyar.database.entities.MarketListEntity


@Database(entities = [MarketListEntity::class] , version = 2)
@TypeConverters(AllMarketModelConverter::class)
//@TypeConverters(AllMarketModelConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun roomDao(): RoomDao
}