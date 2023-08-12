package com.mohammedragab.innovaalomovesmock.datalayer.basemodel

sealed class DataState{
    object Loading : DataState()
    data class Error(val message: String?="") : DataState()
    data class Complete(val message: String?="") : DataState()
    data class IDLE(val message: String?="") : DataState()


}
