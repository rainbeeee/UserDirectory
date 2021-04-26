package com.rbnb.userdirectory.database.account

class AccountRepository(private val accountDao: AccountDao) {

    suspend fun getAccount(username: String?, password: String?): Account? {
        return accountDao.getAccount(username, password)
    }
}