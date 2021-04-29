package com.rbnb.orangepages.database.account

import android.content.SharedPreferences
import androidx.core.content.edit
import com.rbnb.orangepages.Constants
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao,
    private val sharedPref: SharedPreferences
) {

    suspend fun getAccount(username: String?, password: String?): Account? {
        return accountDao.getAccount(username, password)
    }

    fun isSignedIn() =
        sharedPref.getBoolean(Constants.KEY_KEEP_SIGNED_IN, false)

    fun keepSignedIn(shouldKeepSignedIn: Boolean) {
        sharedPref.edit(commit = true) {
            putBoolean(
                Constants.KEY_KEEP_SIGNED_IN,
                shouldKeepSignedIn
            )
        }
    }

    fun logOut() =
        sharedPref.edit(commit = true) {
            putBoolean(
                Constants.KEY_KEEP_SIGNED_IN,
                false
            )
        }
}