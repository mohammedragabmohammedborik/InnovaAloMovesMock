package com.mohammedragab.innovaalomovesmock.datalayer

import com.mohammedragab.innovaalomovesmock.datalayer.alomovedatamodel.TrainingSeriesData
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.BaseNetworkResponseModelResponse
import com.mohammedragab.innovaalomovesmock.datalayer.networkbase.NetworkResult
import retrofit2.http.*

interface AloMoveApi {
    @GET("innovw/alomoves/trainingseries")
    suspend fun getTrainingSeries( )
    : NetworkResult<BaseNetworkResponseModelResponse<TrainingSeriesData
            >>





}