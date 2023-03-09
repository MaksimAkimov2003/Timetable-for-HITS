package com.example.groups_screen.data.dataSource

import com.example.groups_screen.data.api.GroupsApi
import com.example.groups_screen.data.model.GroupModel

class GroupDataSource(private val api: GroupsApi) {

	suspend fun getGroupsList(disciplineId: String): List<GroupModel> {
		return api.getDirections(disciplineId)
	}
}