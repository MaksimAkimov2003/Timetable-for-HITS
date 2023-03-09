package com.example.groups_screen.di

import com.example.groups_screen.data.api.GroupsApi
import com.example.groups_screen.data.dataSource.GroupDataSource
import com.example.groups_screen.data.repository.GroupsRepository
import com.example.groups_screen.domain.useCase.GetGroupsListUseCase
import com.example.groups_screen.presentation.GroupsViewModel
import com.example.network.changer.getRetrofit
import com.example.network.retrofit.createRetrofitService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val groupsScreenModule = module {
	viewModel {
		GroupsViewModel(getGroupsListUseCase = get())
	}
	factory { GetGroupsListUseCase(repository = get()) }
	single { GroupsRepository(dataSource = get()) }
	single { GroupDataSource(api = get()) }
	factory<GroupsApi> { createRetrofitService(retrofit = getRetrofit()) }
}