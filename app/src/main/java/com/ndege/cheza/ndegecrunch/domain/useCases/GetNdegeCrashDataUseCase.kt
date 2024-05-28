package com.ndege.cheza.ndegecrunch.domain.useCases

import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeCrashDTO
import com.ndege.cheza.ndegecrunch.domain.repositories.NdegeCrashRepository
import com.ndege.cheza.ndegecrunch.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNdegeCrashDataUseCase @Inject constructor(private val ndegeCrashRepository: NdegeCrashRepository) {
    operator fun invoke() : Flow<Resource<NdegeCrashDTO>> = flow {
        try {
            emit(Resource.Loading(true))
            val data = ndegeCrashRepository.getNdegeCrashData()
            emit(Resource.Success(data))
            emit(Resource.Loading(false))
        }
        catch (e: IOException){
            emit(Resource.Loading(false))
            emit(Resource.Error("Please check your internet connection"))
        }
        catch (e: HttpException){
            emit(Resource.Loading(false))
            emit(Resource.Error(e.localizedMessage ?: "unexpected error occurred"))
        }
    }
}