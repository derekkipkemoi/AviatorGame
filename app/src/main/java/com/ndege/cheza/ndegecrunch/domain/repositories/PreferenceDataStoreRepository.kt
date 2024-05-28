package com.ndege.cheza.ndegecrunch.domain.repositories

import kotlinx.coroutines.flow.Flow


interface PreferenceDataStoreRepository {
    suspend fun saveUserIsLoggedIn(loggedIn: Boolean)
    suspend fun getUserIsLoggedIn(): Flow<Boolean>

    suspend fun savePhoneNumber(phoneNumber: String)
    suspend fun getPhoneNumber() : Flow<String>

    suspend fun savePassword(password: String)
    suspend fun getPassword() : Flow<String>

    suspend fun setBetAmount(amount: String)
    suspend fun getBetAmount(): Flow<String>

    suspend fun setShowAnimation(show: Boolean)
    suspend fun getShowAnimation() : Flow<Boolean>

    suspend fun saveMpesaCode(code: String)
    suspend fun getMpesaCode() : Flow<String>

    suspend fun saveAccountBalance(amount: Double)
    suspend fun getAccountBalance() : Flow<Double>
}