package com.example.catphotos.data.remote

import com.example.catphotos.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class TheCatApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(Constants.apiKeyName, Constants.apiKeyValue)
        return chain.proceed(request.build())
    }
}