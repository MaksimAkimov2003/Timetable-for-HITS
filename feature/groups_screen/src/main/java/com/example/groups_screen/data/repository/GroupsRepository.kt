package com.example.groups_screen.data.repository

import com.example.groups_screen.data.dataSource.GroupDataSource
import com.example.groups_screen.data.mapper.toEntity
import com.example.groups_screen.domain.entity.GroupEntity

class GroupsRepository(private val dataSource: GroupDataSource) {

	suspend fun getGroupsFromApi(disciplineId: String): List<GroupEntity> {
		return dataSource.getGroupsList(disciplineId).toEntity()
	}
}