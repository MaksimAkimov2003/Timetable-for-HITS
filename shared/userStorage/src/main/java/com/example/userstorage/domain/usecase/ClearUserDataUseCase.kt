package com.example.userstorage.domain.usecase

import com.example.userstorage.domain.repository.IUserStorageRepository

class ClearUserDataUseCase(private val repository: IUserStorageRepository) {

    operator fun invoke() {
        repository.clearUserData()
    }

}