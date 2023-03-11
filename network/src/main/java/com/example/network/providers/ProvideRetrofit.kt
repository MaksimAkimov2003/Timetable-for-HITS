package com.example.network.providers

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
