package com.ndege.cheza.ndegecrunch.data.remote

import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeCrashDTO
import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeDetails
import retrofit2.http.GET

interface NdegeCrashAPI {
    @GET("/NdegeCrash/ndegecrash.json")
    suspend fun getNdegeCrashData() : NdegeCrashDTO

    @GET("/NdegeCranch/ndege.json")
    suspend fun getNdegeDetails(): NdegeDetails
}