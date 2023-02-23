package com.example.timetable_for_hits.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideRetrofit(
	okHttpClient: OkHttpClient,
	url: String
): Retrofit = Retrofit.Builder()
	.addConverterFactory(GsonConverterFactory.create())
	.client(okHttpClient)
	.baseUrl(url)
	.build()
