package com.rbnb.orangepages.database.account

import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountDao: AccountDao) {

    suspend fun getAccount(username: String?, password: String?): Account? {
        return accountDao.getAccount(username, password)
    }
}