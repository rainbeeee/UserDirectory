package com.rbnb.orangepages.login

data class LoginCredentials(
    val username: String?,
    val password: String?
) {
    val isValid: Boolean
        get() = (username?.isNotBlank() ?: false) && (password?.isNotBlank() ?: false)
}
