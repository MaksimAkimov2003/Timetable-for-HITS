package com.example.network.providers

import okhttp3.logging.HttpLoggingInterceptor

fun provideLoggingInterceptor(): HttpLoggingInterceptor =
	HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)