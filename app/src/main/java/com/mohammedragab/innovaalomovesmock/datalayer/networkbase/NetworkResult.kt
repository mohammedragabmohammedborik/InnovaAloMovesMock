package com.mohammedragab.innovaalomovesmock.datalayer.networkbase
sealed class NetworkResult<T>{
    class  NetworkSuccess<T>(val data:T): NetworkResult<T>()
    class NetworkError<T:Any>(val code:Int, val message:String?): NetworkResult<T>()
    class NetworkException<T:Any>(val throwable: Throwable): NetworkResult<T>()
}

