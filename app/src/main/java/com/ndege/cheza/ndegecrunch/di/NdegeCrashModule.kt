package com.ndege.cheza.ndegecrunch.di

import android.content.Context
import com.ndege.cheza.ndegecrunch.data.local.repositories.PreferenceDataStoreImpl
import com.ndege.cheza.ndegecrunch.data.remote.NdegeCrashAPI
import com.ndege.cheza.ndegecrunch.data.remote.repositories.NdegeCrashRepositoryImp
import com.ndege.cheza.ndegecrunch.domain.repositories.NdegeCrashRepository
import com.ndege.cheza.ndegecrunch.domain.repositories.PreferenceDataStoreRepository
import com.ndege.cheza.ndegecrunch.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NdegeCrashModule {

    @Provides
    @Singleton
    fun providesNdegeCrashAPI() : NdegeCrashAPI =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NdegeCrashAPI::class.java)

    @Provides
    @Singleton
    fun providesNdegeCrashRepository(api: NdegeCrashAPI) : NdegeCrashRepository =
        NdegeCrashRepositoryImp(api)

    @Provides
    @Singleton
    fun providesPreferenceDataStoreRepository(@ApplicationContext context: Context) : PreferenceDataStoreRepository =
       PreferenceDataStoreImpl(context)

}