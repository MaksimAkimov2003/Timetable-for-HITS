package com.example.userstorage.domain.usecase

import com.example.userstorage.domain.entity.UserData
import com.example.userstorage.domain.repository.IUserStorageRepository

class SaveUserDataUseCase(private val repository: IUserStorageRepository) {

    operator fun invoke(data: UserData) {
        repository.saveUserData(data)
    }

}