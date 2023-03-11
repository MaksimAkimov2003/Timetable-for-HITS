package com.example.userstorage.di

import android.content.Context
import com.example.userstorage.data.repository.UserStorageRepository
import com.example.userstorage.data.storage.IUserStorage
import com.example.userstorage.data.storage.UserStorage
import com.example.userstorage.domain.repository.IUserStorageRepository
import com.example.userstorage.domain.usecase.ClearUserDataUseCase
import com.example.userstorage.domain.usecase.GetUserDataUseCase
import com.example.userstorage.domain.usecase.SaveUserDataUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val userStorageKey = "USER_STORAGE_KEY"
val userStorageModule = module {
    factory<IUserStorage> { UserStorage(sharedPrefs = androidContext().getSharedPreferences(userStorageKey, Context.MODE_PRIVATE)) }
    factory<IUserStorageRepository> { UserStorageRepository(storage = get()) }
    factory { GetUserDataUseCase(repository = get()) }
    factory { SaveUserDataUseCase(repository = get()) }
    factory { ClearUserDataUseCase(repository = get()) }
}