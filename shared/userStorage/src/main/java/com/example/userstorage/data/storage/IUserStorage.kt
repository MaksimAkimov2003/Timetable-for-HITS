package com.example.userstorage.data.storage

import com.example.userstorage.domain.entity.UserData

interface IUserStorage {

    fun getUserData(): UserData
    fun saveUserData(data: UserData)
    fun clearUserData()

}