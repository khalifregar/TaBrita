package tech.tabrita.com.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.tabrita.com.data.remote.TaBritaApiService
import javax.inject.Singleton

/**
 * Provides Retrofit + API service for fetching real rich articles
 * from the local Python scraper API (news-scraper/scripts/api_server.py).
 *
 * IMPORTANT for dev:
 * - Android Emulator: "http://10.0.2.2:8000/"
 * - Physical device on same WiFi: use your PC LAN IP, e.g. "http://192.168.1.5:8000/"
 *   or run: adb reverse tcp:8000 tcp:8000 then "http://127.0.0.1:8000/"
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // For physical phone on same WiFi, use your PC's LAN IP (e.g. from ipconfig)
    // Current dev machine IP: 192.168.100.43
    private const val BASE_URL = "http://192.168.100.43:8000/"

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideTaBritaApiService(retrofit: Retrofit): TaBritaApiService =
        retrofit.create(TaBritaApiService::class.java)
}
