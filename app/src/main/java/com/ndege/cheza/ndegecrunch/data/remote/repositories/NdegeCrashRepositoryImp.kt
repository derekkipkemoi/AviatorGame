package com.ndege.cheza.ndegecrunch.data.remote.repositories

import com.ndege.cheza.ndegecrunch.data.remote.NdegeCrashAPI
import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeCrashDTO
import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeDetails
import com.ndege.cheza.ndegecrunch.domain.repositories.NdegeCrashRepository
import javax.inject.Inject

class NdegeCrashRepositoryImp @Inject constructor(private val api: NdegeCrashAPI) : NdegeCrashRepository{
    override suspend fun getNdegeCrashData(): NdegeCrashDTO =
        api.getNdegeCrashData()
    override suspend fun getNdegeDetails(): NdegeDetails =
        api.getNdegeDetails()
}