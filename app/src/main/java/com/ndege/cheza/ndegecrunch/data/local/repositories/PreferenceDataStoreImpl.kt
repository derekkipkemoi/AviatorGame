package com.ndege.cheza.ndegecrunch.data.local.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ndege.cheza.ndegecrunch.domain.repositories.PreferenceDataStoreRepository
import com.ndege.cheza.ndegecrunch.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.DATA_STORE_NAME)
class PreferenceDataStoreImpl @Inject constructor(private val context: Context) : PreferenceDataStoreRepository {
    companion object {
        val PHONE = stringPreferencesKey("PHONE_NUMBER")
        val PASSWORD = stringPreferencesKey("PASSWORD")
        val LOGGED_IN = booleanPreferencesKey("USER_LOGGED_IN")
        val BET_AMOUNT = stringPreferencesKey("BET_AMOUNT")
        val SHOW_ANIMATION = booleanPreferencesKey("SHOW_ANIMATION")
        val MPESA_CODE = stringPreferencesKey("MPESA_CODE")
        val ACCOUNT_BALANCE = doublePreferencesKey("ACCOUNT_BALANCE")
    }

    override suspend fun saveUserIsLoggedIn(loggedIn: Boolean) {
       context.dataStore.edit {
           it[LOGGED_IN] = loggedIn
       }
    }

    override suspend fun getUserIsLoggedIn(): Flow<Boolean> =
        context.dataStore.data.catch {exceptions ->
            if (exceptions is IOException ){
                emit(emptyPreferences())
            }else throw exceptions
        }.map { preferences ->
            preferences[LOGGED_IN] ?: false
        }

    override suspend fun setBetAmount(amount: String) {
        context.dataStore.edit {
            it[BET_AMOUNT] = amount
        }
    }

    override suspend fun getBetAmount(): Flow<String> =
        context.dataStore.data.catch { exceptions ->
            if (exceptions is IOException) {
                emit(emptyPreferences())
            }else throw exceptions
        }.map { preferences ->
          preferences[BET_AMOUNT] ?: "10.00"
        }

    override suspend fun setShowAnimation(show: Boolean) {
        context.dataStore.edit {
            it[SHOW_ANIMATION] = show
        }
    }

    override suspend fun getShowAnimation(): Flow<Boolean> =
        context.dataStore.data.catch { exception ->
            if(exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map {
            it[SHOW_ANIMATION] ?: true
        }


    override suspend fun savePhoneNumber(phoneNumber: String) {
       context.dataStore.edit {
           it[PHONE] = phoneNumber
       }
    }

    override suspend fun getPhoneNumber(): Flow<String> =
        context.dataStore.data.catch { exceptions ->
            if (exceptions is IOException)
                emit(emptyPreferences())
            else throw exceptions
        }.map {
            it[PHONE] ?: ""
        }

    override suspend fun savePassword(password: String) {
       context.dataStore.edit {
           it[PASSWORD] = password
       }
    }

    override suspend fun getPassword(): Flow<String> =
        context.dataStore.data.catch { exceptions ->
            if (exceptions is IOException) emit(emptyPreferences())
            else throw  exceptions
        }.map {
            it[PASSWORD] ?: ""
        }

    override suspend fun saveMpesaCode(code: String) {
        context.dataStore.edit {
            it[MPESA_CODE] = code
        }
    }

    override suspend fun getMpesaCode(): Flow<String> =
        context.dataStore.data.catch { exceptions ->
            if (exceptions is IOException) emit(emptyPreferences())
            else throw exceptions
        }.map {
            it[MPESA_CODE] ?: "DSGHTRD453"
        }

    override suspend fun saveAccountBalance(amount: Double) {
       context.dataStore.edit{
           it[ACCOUNT_BALANCE] = amount
       }
    }

    override suspend fun getAccountBalance(): Flow<Double> =
        context.dataStore.data.catch{ exceptions ->
            if (exceptions is IOException) emit(emptyPreferences())
            else throw exceptions
        }.map {
            it[ACCOUNT_BALANCE] ?: 0.00
        }
}