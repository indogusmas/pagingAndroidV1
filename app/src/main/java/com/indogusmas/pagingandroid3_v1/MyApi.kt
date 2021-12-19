package com.indogusmas.pagingandroid3_v1

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("passenger")
    suspend fun getPassengersData(
        @Query("page") page:Int,
        @Query("size") size: Int = 10
    ): PassengersResponse

    companion object{
        private const val  BASE_URL = "https://api.instantwebtools.net/v1/"
        fun  create(): MyApi{
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}