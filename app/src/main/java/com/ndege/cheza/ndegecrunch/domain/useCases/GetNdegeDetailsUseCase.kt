package com.ndege.cheza.ndegecrunch.domain.useCases


import android.app.Application
import android.util.Log
import android.widget.Toast
import com.ndege.cheza.ndegecrunch.data.remote.dto.NdegeDetails
import com.ndege.cheza.ndegecrunch.domain.repositories.NdegeCrashRepository
import com.ndege.cheza.ndegecrunch.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNdegeDetailsUseCase @Inject constructor(private val getNdegeCrashRepository: NdegeCrashRepository) {
    operator fun invoke(): Flow<Resource<NdegeDetails>> = flow {
        try {
            emit(Resource.Loading(true))
            val details = getNdegeCrashRepository.getNdegeDetails()
            emit(Resource.Success(details))
            emit(Resource.Loading(false))
        } catch (e: IOException) {
            emit(Resource.Loading(false))
            emit(Resource.Error("Make sure your phone has an active internet connection"))
        } catch (e: HttpException) {
            Toast.makeText(Application().baseContext, e.toString(), Toast.LENGTH_LONG).show()
            Log.i("Error1", e.toString())
            emit(Resource.Loading(false))
            emit(Resource.Error(e.message))
        }
    }
}