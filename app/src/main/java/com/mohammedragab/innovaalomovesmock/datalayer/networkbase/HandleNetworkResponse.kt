package com.mohammedragab.innovaalomovesmock.datalayer.networkbase
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response


fun <T : Any> handleApi(
    execute:  () -> Response<T>
): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.NetworkSuccess(body)
        } else {
            response.errorBody()
            val gson = Gson()
            val errorResponse: ErrorResponse = gson.fromJson(
                response.errorBody()?.string(),
                ErrorResponse::class.java
            )
            NetworkResult.NetworkError(
                code = errorResponse.status_code,
                message = errorResponse.message
            )
        }
    } catch (e: HttpException) {
        NetworkResult.NetworkError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.NetworkException(e)
    }
}

// extentions
suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.NetworkSuccess<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.NetworkError<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> NetworkResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkResult.NetworkException<T>) {
        executable(throwable)
    }
}