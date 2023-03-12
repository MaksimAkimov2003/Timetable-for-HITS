package com.example.userstorage.domain.repository

import com.example.userstorage.domain.entity.UserData

interface IUserStorageRepository {

    fun getUserData(): UserData
    fun saveUserData(data: UserData)
    fun clearUserData()

}