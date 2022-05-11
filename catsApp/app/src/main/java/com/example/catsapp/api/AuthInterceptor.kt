package com.example.catsapp.api
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor () : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .header("Authorization", "Client-ID 1ceddedc03a5d71")
        return chain.proceed(newRequest.build())
    }
}