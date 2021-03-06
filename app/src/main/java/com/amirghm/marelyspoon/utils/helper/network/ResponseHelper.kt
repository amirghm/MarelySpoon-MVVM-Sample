package com.amirghm.marelyspoon.utils.helper.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * ResponseHelper
 *
 * an abstract class to handle suspend tasks and map results into {@see Result}
 * */

abstract class ResponseHelper {
    /**
     * getResult
     *
     * launch a suspend retrofit function and return the result in Result type
     *
     * @param call the suspend function
     * @return Result<T> the result of task mapped into Result
     * */
    private suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }

            return Result.error(ErrorMapper().mapToDomainErrorException(response))
        } catch (e: Exception) {
            return Result.error(ErrorMapper().mapToDomainErrorException(e))
        }
    }

    /**
     * getResultAsFlow
     *
     * launch a suspend retrofit function and return the result in Result type as coroutine flow
     *
     * @param call the suspend function
     * @return Result<T> the result of task mapped into Result
     * */
    protected fun <T> getResultAsFlow(call: suspend () -> Response<T>): Flow<Result<T?>> = flow {
        val response = getResult(call)

        when (response.status) {
            Result.Status.SUCCESS -> emit(Result.success(response.data))
            Result.Status.ERROR -> emit(Result.error<T>(response.errorModel))
        }
    }
}