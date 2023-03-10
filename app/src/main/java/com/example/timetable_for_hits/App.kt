package com.example.timetable_for_hits

import android.app.Application
import com.example.network.di.networkModule
import com.example.userstorage.di.userStorageModule
import com.example.weektimetable.di.weekTimetableModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)

			modules(
				networkModule,
				weekTimetableModule,
				userStorageModule
			)
		}
	}
}