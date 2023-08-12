package com.mohammedragab.innovaalomovesmock.datalayer.alomoverepository

import com.mohammedragab.innovaalomovesmock.datalayer.AloMoveApi
import com.mohammedragab.innovaalomovesmock.datalayer.alomovedatamodel.TrainingSeriesData
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.BaseNetworkResponseModelResponse
import com.mohammedragab.innovaalomovesmock.datalayer.networkbase.NetworkResult

class AloMoveRepositoryImpl(val aloMoveApi: AloMoveApi): AloMoveRepository {
    override suspend fun getAloMov(): NetworkResult<BaseNetworkResponseModelResponse<TrainingSeriesData>> {
       return aloMoveApi.getTrainingSeries()
    }

}