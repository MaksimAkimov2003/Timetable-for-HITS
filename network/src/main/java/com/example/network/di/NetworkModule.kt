package com.example.network.di

import com.example.network.changer.MOCK
import com.example.network.changer.NetworkChanger
import com.example.network.changer.ORIGINAL
import com.example.mockapiserver.interceptor.FakeDataInterceptor
import com.example.network.providers.provideLoggingInterceptor
import com.example.network.providers.provideOkHttpClient
import com.example.network.providers.provideRetrofit
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
	single { provideLoggingInterceptor() }
	single { FakeDataInterceptor(androidContext()) }
	single { NetworkChanger(androidContext()) }

	single(named(ORIGINAL)) {
		provideOkHttpClient(
			interceptor = get<HttpLoggingInterceptor>()
		)
	}
	single(named(MOCK)) {
		provideOkHttpClient(
			interceptor = get<FakeDataInterceptor>()
		)
	}
	single(named(ORIGINAL)) {
		provideRetrofit(
			okHttpClient = get(named(ORIGINAL)),
			url = "https://our.api"
		)
	}
	single(named(MOCK)) {
		provideRetrofit(
			okHttpClient = get(named(MOCK)),
			url = "https://our.api"
		)
	}
}