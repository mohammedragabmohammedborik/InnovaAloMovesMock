package com.mohammedragab.innovaalomovesmock.datalayer.basemodel

inline  fun <T> mapper(from: BaseNetworkResponseModelResponse<T>): ResultData<T> {
    return ResultData(result = from.result,from.message)
}