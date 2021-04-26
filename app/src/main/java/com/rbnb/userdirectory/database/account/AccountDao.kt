package com.rbnb.userdirectory.database.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts WHERE username = :username AND password = :password")
    suspend fun getAccount(username: String?, password: String?): Account?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(account: Account)
}