package com.rbnb.userdirectory.database.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String
)