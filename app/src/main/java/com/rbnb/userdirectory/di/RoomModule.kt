package com.rbnb.userdirectory.di

import android.content.Context
import androidx.room.Room
import com.rbnb.userdirectory.database.UserDirectoryDatabase
import com.rbnb.userdirectory.database.account.AccountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "user_directory_database"

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): UserDirectoryDatabase =
        Room.databaseBuilder(
            appContext,
            UserDirectoryDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideAccountDao(database: UserDirectoryDatabase): AccountDao = database.accountDao
}