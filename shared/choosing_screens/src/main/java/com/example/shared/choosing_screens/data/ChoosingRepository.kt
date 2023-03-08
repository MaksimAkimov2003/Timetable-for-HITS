package com.example.shared.choosing_screens.data

interface ChoosingRepository {

	fun getList(id: String? = null): List<Any>
}