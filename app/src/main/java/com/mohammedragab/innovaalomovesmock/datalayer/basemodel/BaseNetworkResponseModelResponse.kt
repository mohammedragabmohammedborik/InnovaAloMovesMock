package com.mohammedragab.innovaalomovesmock.datalayer.basemodel

data class BaseNetworkResponseModelResponse<T>(val status_code:Int, val message:String?, val success:Boolean, val result:T )
