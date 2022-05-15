/*
package com.example.maktab_q4.data.remote.network

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response? {
        val response : Response? = null
        val interceptor = Interceptor { chain ->
            val oldRequest = chain.request()
            val newRequest = oldRequest.newBuilder()
                .addHeader("Authorization", "Bearer maalekghoba")
                .build()
            val response = chain.proceed(newRequest)
            response
        }

        return response
    }

}*/
