package com.rbnb.userdirectory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rbnb.userdirectory.database.account.Account
import com.rbnb.userdirectory.database.account.AccountDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class UserDirectoryDatabase : RoomDatabase() {

    abstract val accountDao: AccountDao

    companion object {

        private const val DB_NAME = "user_directory_database"
        private val CREDENTIAL_1 = Pair("user1", "pass1")
        private val CREDENTIAL_2 = Pair("user2", "pass2")
        private val CREDENTIAL_3 = Pair("user3", "pass3")

        @Volatile
        private var INSTANCE: UserDirectoryDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): UserDirectoryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDirectoryDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.accountDao)
                    }
                }
            }
        }

        suspend fun populateDatabase(accountDao: AccountDao) {
            val account1 = Account(CREDENTIAL_1.first, CREDENTIAL_1.second)
            accountDao.insert(account1)
            val account2 = Account(CREDENTIAL_2.first, CREDENTIAL_2.second)
            accountDao.insert(account2)
            val account3 = Account(CREDENTIAL_3.first, CREDENTIAL_3.second)
            accountDao.insert(account3)
        }
    }
}