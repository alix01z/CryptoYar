package com.alix01z.cryptoyar.di.modules

import android.app.Application
import androidx.room.Room
import com.alix01z.cryptoyar.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltRoomModule {

    @Provides
    fun provideRoomDB(application: Application): AppDatabase =
        Room.databaseBuilder(application , AppDatabase::class.java , "MarketListDB")
            .fallbackToDestructiveMigration()
            .build()
}