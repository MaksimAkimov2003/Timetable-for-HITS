package com.example.mockapiserver.changer

import android.content.Context
import androidx.core.content.edit
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

const val MOCK = "MOCK_SERVER"
const val ORIGINAL = "ORIGINAL_SERVER"

class NetworkChanger(context: Context) {

	private val preferences = context.getSharedPreferences("NETWORK_CHANGER", Context.MODE_PRIVATE)

	var networkState: String = preferences.getString("NETWORK_STATE", MOCK) ?: MOCK
		private set

	fun mockOn() {
		this.networkState = MOCK

		preferences.edit {
			putString("NETWORK_STATE", this@NetworkChanger.networkState)
			commit()
		}
	}

	fun mockOf() {
		this.networkState = ORIGINAL
		preferences.edit {
			putString("NETWORK_STATE", this@NetworkChanger.networkState)
			commit()
		}
	}

	fun isMock(): Boolean =
		networkState == MOCK
}

inline fun <reified T> Scope.getRetrofit(): T {
	val connectionType = get<NetworkChanger>().networkState

	return get(named(connectionType))
}