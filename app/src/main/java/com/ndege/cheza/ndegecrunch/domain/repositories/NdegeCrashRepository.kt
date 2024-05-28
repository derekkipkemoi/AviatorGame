package com.ndege.cheza.ndegecrunch.domain.repositories

import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeCrashDTO
import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeDetails

interface NdegeCrashRepository {
    suspend fun getNdegeCrashData() : NdegeCrashDTO
    suspend fun getNdegeDetails(): NdegeDetails
}