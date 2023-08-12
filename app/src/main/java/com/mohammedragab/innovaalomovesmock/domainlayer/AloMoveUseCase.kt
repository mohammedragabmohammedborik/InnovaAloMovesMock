package com.mohammedragab.innovaalomovesmock.domainlayer
import com.mohammedragab.innovaalomovesmock.datalayer.alomovedatamodel.TrainingSeriesData
import com.mohammedragab.innovaalomovesmock.datalayer.alomoverepository.AloMoveRepository
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.NetworkResultData
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.mapper
import com.mohammedragab.innovaalomovesmock.datalayer.networkbase.NetworkResult
import com.mohammedragab.innovaalomovesmock.domainlayer.baseusecase.UseCase

class AloMoveUseCase constructor(

    private val aloMoveRepository: AloMoveRepository
) : UseCase<NetworkResultData<TrainingSeriesData>, Any>() {
    override suspend fun executeOnBackground(inff: Any): NetworkResultData<TrainingSeriesData> {
        val response= aloMoveRepository.getAloMov()
        return when(response){
            is NetworkResult.NetworkSuccess->{

                val data=response.data
              val ss=  mapper(data)

                NetworkResultData.DataSuccess(ss.result)
            }
            is NetworkResult.NetworkError->{

                NetworkResultData.DataError(response.code,response.message)
            }
            is NetworkResult.NetworkException->{
                NetworkResultData.DataException(response.throwable)
            }
            else -> {
                NetworkResultData.DataError(401,"un known")
            }
        }



    }


}