package com.testtask.movies.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api-key", Key.API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    object Key {
        const val API_KEY = "3lRsuK4mD8YXtQYLneKjPlJ0rwqYOo4J"
    }
}