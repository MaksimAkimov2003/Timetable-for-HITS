package com.example.userstorage.domain.usecase

import com.example.userstorage.domain.entity.UserData
import com.example.userstorage.domain.repository.IUserStorageRepository

class GetUserDataUseCase(private val repository: IUserStorageRepository) {

    operator fun invoke(): UserData {
        return repository.getUserData()
    }

}