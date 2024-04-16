package com.alix01z.cryptoyar.di.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.alix01z.cryptoyar.database.AppDatabase
import com.alix01z.cryptoyar.database.RoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideRoomDao(appDatabase: AppDatabase): RoomDao {
        return appDatabase.roomDao()
    }

    @Singleton
    @Provides
    fun provideRoomDB(application: Application): AppDatabase {
        return Room.databaseBuilder(application , AppDatabase::class.java , "marketlist.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}