package com.example.maktab_q4.di

import com.example.maktab_q4.data.remote.network.RemoteDataSource
import com.example.maktab_q4.data.remote.network.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.mohsenafshar.apps.mkbarchitecture.data.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val BASE_URL = "http://51.195.19.222:3000/api/v1/"


    @Provides
    @IoDispatcher
    fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun providesCustomInterceptor(): Interceptor = Interceptor { chain ->
        val oldRequest = chain.request()
        val newRequest = oldRequest.newBuilder()
            .addHeader("Authorization", "Bearer maalekghoba")
            .build()
        val response = chain.proceed(newRequest)
        response
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, myInterceptor: Interceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(myInterceptor)
            .build()


    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient,gsonConverterFactory: GsonConverterFactory): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()


    @Singleton
    @Provides
    fun providesRetrofitService(retrofit: Retrofit):UserApi = retrofit.create(UserApi::class.java)



}