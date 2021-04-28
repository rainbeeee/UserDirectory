package com.rbnb.orangepages.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rbnb.orangepages.database.account.Account
import com.rbnb.orangepages.database.account.AccountDao

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class OrangePagesDatabase : RoomDatabase() {

    abstract val accountDao: AccountDao
}