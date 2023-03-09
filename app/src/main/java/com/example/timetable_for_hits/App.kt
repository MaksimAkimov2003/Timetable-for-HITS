package com.example.timetable_for_hits

import android.app.Application
import com.example.directions_screen.di.directionsScreenModule
import com.example.faculties_screen.di.facultiesScreenModule
import com.example.feature.teachers_screen.di.teachersScreenModule
import com.example.mainmenu.di.mainMenuModule
import com.example.network.di.networkModule
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
				teachersScreenModule,
				facultiesScreenModule,
				directionsScreenModule,
				mainMenuModule
			)
		}
	}
}