package com.example.network.providers

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val TIMEOUT: Long = 60

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
	OkHttpClient().newBuilder().apply {
		connectTimeout(TIMEOUT, TimeUnit.SECONDS)
		writeTimeout(TIMEOUT, TimeUnit.SECONDS)
		readTimeout(TIMEOUT, TimeUnit.SECONDS)
		addInterceptor(interceptor)
	}.build()
