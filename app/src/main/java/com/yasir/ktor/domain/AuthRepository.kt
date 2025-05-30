package com.yasir.ktor.domain

interface AuthRepository {

    suspend fun getUserName(): String

    suspend fun getAddress(): String
}