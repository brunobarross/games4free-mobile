package com.altamirobruno.games4free.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPClient {
  private const val BASE_URL = "https://gamerpower.p.rapidapi.com"
  const val API_KEY = "59df7faf5emsh2cb45c52d4b33e3p18956fjsn99432fdbfb5d"

  private fun httpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val headerInterceptor = Interceptor { chain ->
      val request = chain.request().newBuilder()
        .addHeader("X-RapidAPI-Key", API_KEY)
        .addHeader("X-RapidAPI-Host", "gamerpower.p.rapidapi.com")
        .build()
      chain.proceed(request)
    }
    return OkHttpClient.Builder()
      .addInterceptor(logging)
      .addInterceptor(headerInterceptor)
      .build()

  }

  fun retrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .client(httpClient())
      .build()
  }
}
