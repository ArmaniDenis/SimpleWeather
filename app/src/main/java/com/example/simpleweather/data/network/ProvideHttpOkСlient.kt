package com.example.simpleweather.data.network

import okhttp3.OkHttpClient

interface ProvideHttpOkClient {
    fun getUnsafeOkHttpClient(): OkHttpClient
    fun getSafeOkHttpClient(): OkHttpClient
}