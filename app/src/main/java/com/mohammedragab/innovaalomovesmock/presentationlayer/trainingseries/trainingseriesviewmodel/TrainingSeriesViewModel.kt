package com.mohammedragab.innovaalomovesmock.presentationlayer.trainingseries.trainingseriesviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohammedragab.innovaalomovesmock.datalayer.alomovedatamodel.TrainingSeriesData
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.DataState
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.onError
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.onException
import com.mohammedragab.innovaalomovesmock.datalayer.basemodel.onSuccess
import com.mohammedragab.innovaalomovesmock.domainlayer.AloMoveUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class TrainingSeriesViewModel(
   val aloMoveUseCase: AloMoveUseCase

) : ViewModel() {

    private val _dataState: MutableStateFlow<DataState> =
        MutableStateFlow(DataState.IDLE(""))
    val dataState: StateFlow<DataState> get() = _dataState

    private val _dataTrainingSeries: MutableStateFlow<TrainingSeriesData?> =
        MutableStateFlow<TrainingSeriesData?>(null)
    val dataTrainingSeries: StateFlow<TrainingSeriesData?> get() = _dataTrainingSeries

    fun getTrainingSeries(){
        _dataState.value = DataState.Loading
        aloMoveUseCase.execute(Any()) { result ->
            result.onSuccess {
                _dataState.value = DataState.Complete()
                _dataTrainingSeries.value = it

            }.onError { code, message ->
                _dataState.value = DataState.Error(message)


            }.onException {
                _dataState.value = DataState.Error(it.message)

            }


        }
    }


}


