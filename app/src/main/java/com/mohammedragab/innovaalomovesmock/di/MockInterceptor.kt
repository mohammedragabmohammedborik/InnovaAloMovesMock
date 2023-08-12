package com.mohammedragab.innovaalomovesmock.di

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody


class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.endsWith("trainingseries") -> getListOfReposBeingStarredJson
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()

    }

}

const val getListOfReposBeingStarredJson = """
{
  "status" : 200,
  "success":true,
  "message":"success"
  "result":{"cover_photo":"image_url",
  "series_name":"The Challenge"
  "coach_name":"mr mohammed "
  "over_view":"more details"
  
  } 
  
}
"""