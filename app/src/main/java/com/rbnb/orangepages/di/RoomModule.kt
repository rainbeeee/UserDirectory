package com.rbnb.orangepages.di

import android.content.Context
import androidx.room.Room
import com.rbnb.orangepages.Constants.DB_DIRECTORY
import com.rbnb.orangepages.Constants.DB_NAME
import com.rbnb.orangepages.database.OrangePagesDatabase
import com.rbnb.orangepages.database.account.AccountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): OrangePagesDatabase =
        Room.databaseBuilder(
            appContext,
            OrangePagesDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .createFromAsset(DB_DIRECTORY)
            .build()

    @Provides
    fun provideAccountDao(database: OrangePagesDatabase): AccountDao = database.accountDao
}