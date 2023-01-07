package com.beatrice.moviesapp.core.network.di

import com.beatrice.moviesapp.BuildConfig
import com.beatrice.moviesapp.BuildConfig.BASE_URL
import com.beatrice.moviesapp.core.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        converterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ) = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideConverterFactory() = MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideHttpClient(
        authInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideAuthInterceptor() = Interceptor { chain ->
        val accessToken = BuildConfig.TMDB_API_KEY
        val original = chain.request()
        val url = original.url.newBuilder().addQueryParameter("api_key", accessToken).build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}