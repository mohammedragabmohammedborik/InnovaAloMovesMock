package com.mohammedragab.innovaalomovesmock.datalayer.alomoverepository

import com.mohammedragab.innovaalomovesmock.datalayer.alomovedatamodel.TrainingSeriesData
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.BaseNetworkResponseModelResponse
import com.mohammedragab.innovaalomovesmock.datalayer.networkbase.NetworkResult

interface AloMoveRepository {
    suspend fun getAloMov( )
          : NetworkResult<BaseNetworkResponseModelResponse<TrainingSeriesData>>

}
