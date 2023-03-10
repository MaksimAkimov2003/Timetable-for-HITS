package com.example.userstorage.data.repository

import com.example.userstorage.data.storage.IUserStorage
import com.example.userstorage.domain.entity.UserData
import com.example.userstorage.domain.repository.IUserStorageRepository

class UserStorageRepository(private val storage: IUserStorage): IUserStorageRepository {

    override fun getUserData(): UserData {
        return storage.getUserData()
    }

    override fun saveUserData(data: UserData) {
        storage.saveUserData(data)
    }

}