package com.example.timetable_for_hits.network

import okhttp3.logging.HttpLoggingInterceptor

fun provideLoggingInterceptor(): HttpLoggingInterceptor =
	HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)