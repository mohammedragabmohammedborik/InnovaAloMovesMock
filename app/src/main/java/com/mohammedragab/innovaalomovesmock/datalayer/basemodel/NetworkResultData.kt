package com.mohammedragab.innovaalomovesmock.datalayer.basemodel


sealed class NetworkResultData<T>{
    class  DataSuccess<T>(val data:T): NetworkResultData<T>()
    class DataError<T:Any>(val code:Int, val message:String?): NetworkResultData<T>()
    class DataException<T:Any>(val throwable: Throwable): NetworkResultData<T>()
}

suspend fun <T : Any> NetworkResultData<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResultData<T> = apply {
    if (this is NetworkResultData.DataSuccess<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResultData<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): NetworkResultData<T> = apply {
    if (this is NetworkResultData.DataError<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> NetworkResultData<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResultData<T> = apply {
    if (this is NetworkResultData.DataException<T>) {
        executable(throwable)
    }
}