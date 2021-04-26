package com.rbnb.userdirectory

import android.app.Application
import com.rbnb.userdirectory.database.UserDirectoryDatabase
import com.rbnb.userdirectory.database.account.AccountRepository
import com.rbnb.userdirectory.user_list.UserListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber
import timber.log.Timber.DebugTree

class UserDirectoryApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { UserDirectoryDatabase.getInstance(this, applicationScope) }
    val accountRepository by lazy { AccountRepository(database.accountDao) }
    val userListRepository by lazy { UserListRepository() }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}