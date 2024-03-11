package com.example.myapplication.data.di

import com.example.myapplication.data.remote.network.ProductInfoApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val URL = "https://shoppapp.liverpool.com.mx/"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideProductInfoRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(URL)
            .client(provideHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides

    fun provideProductInfoApiClient(retrofit: Retrofit): ProductInfoApiClient{
        return retrofit.create(ProductInfoApiClient::class.java)
    }
}