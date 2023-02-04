package com.example.lukyanovtinkoff.app.di

import com.example.lukyanovtinkoff.data.network.FilmsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "https://kinopoiskapiunofficial.tech/"
    private val TOKEN = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val requestBuilder = request
                .newBuilder()
                .addHeader("X-API-KEY", TOKEN)
            return@addInterceptor chain.proceed(requestBuilder.build())
        }
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideFilmsApi(retrofit: Retrofit): FilmsApi = retrofit.create(FilmsApi::class.java)
}