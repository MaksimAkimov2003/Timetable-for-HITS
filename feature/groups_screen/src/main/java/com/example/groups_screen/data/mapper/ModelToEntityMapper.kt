package com.example.groups_screen.data.mapper

import com.example.groups_screen.data.model.GroupModel
import com.example.groups_screen.domain.entity.GroupEntity

fun List<GroupModel>.toEntity(): List<GroupEntity> = map {
	GroupEntity(
		number = it.groupNumber
	)
}