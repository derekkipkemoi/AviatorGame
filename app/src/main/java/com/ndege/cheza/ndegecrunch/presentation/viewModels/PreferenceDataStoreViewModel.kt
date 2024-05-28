package com.ndege.cheza.ndegecrunch.presentation.viewModels

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndege.cheza.ndegecrunch.domain.repositories.PreferenceDataStoreRepository
import com.ndege.cheza.ndegecrunch.presentation.viewModels.events.PreferenceDataStoreEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PreferenceDataStoreViewModel @Inject constructor(
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository) : ViewModel() {


    fun savePhoneNumber(phoneNumber: String){
        viewModelScope.launch {
            preferenceDataStoreRepository.savePhoneNumber(phoneNumber)
        }
    }

    fun getPhoneNumber() : Flow<String> =
        runBlocking {
            preferenceDataStoreRepository.getPhoneNumber()
        }

    fun savePassword(password: String) {
        viewModelScope.launch {
            preferenceDataStoreRepository.savePassword(password)
        }
    }

    fun getPassword() : Flow<String> =
        runBlocking {
            preferenceDataStoreRepository.getPassword()
        }

    fun setUserLoggedIn(loggedIn: Boolean) {
        viewModelScope.launch {
            preferenceDataStoreRepository.saveUserIsLoggedIn(loggedIn)
        }
    }

    fun getUserLoggedIn() : Flow<Boolean> =
        runBlocking {
            preferenceDataStoreRepository.getUserIsLoggedIn()
        }

    fun setBetAmount(amount: String){
        viewModelScope.launch {
            preferenceDataStoreRepository.setBetAmount(amount)
        }
    }


    fun getBetAmount() : Flow<String> =
        runBlocking {
            preferenceDataStoreRepository.getBetAmount()

        }

    fun setShowAnimation(show: Boolean){
        viewModelScope.launch {
            preferenceDataStoreRepository.setShowAnimation(show)
        }
    }

    fun getShowAnimation() : Flow<Boolean> =
        runBlocking {
            preferenceDataStoreRepository.getShowAnimation()
        }

    fun saveMpesaCode(code: String) {
        viewModelScope.launch {
            preferenceDataStoreRepository.saveMpesaCode(code)
        }
    }

    fun getMpesaCode() : Flow<String> =
        runBlocking {
            preferenceDataStoreRepository.getMpesaCode()
        }


//    fun setAccountBalance(amount: Double){
//        viewModelScope.launch {
//            preferenceDataStoreRepository.saveAccountBalance(amount)
//        }
//    }

    fun getAccountBalance() :Flow<Double> =
        runBlocking {
            preferenceDataStoreRepository.getAccountBalance()
        }


}