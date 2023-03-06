package com.example.shared.choosing_screens

interface ChoosingRepository {

	fun getList(id: String? = null): List<Any>
}