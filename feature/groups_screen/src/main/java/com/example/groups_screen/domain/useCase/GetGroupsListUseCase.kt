package com.example.groups_screen.domain.useCase

import com.example.groups_screen.data.repository.GroupsRepository
import com.example.groups_screen.domain.entity.GroupEntity

class GetGroupsListUseCase(private val repository: GroupsRepository) {

	suspend fun execute(directionId: String): List<GroupEntity> {
		return repository.getGroupsFromApi(directionId)
	}
}