package com.example.weektimetable.data.repository

import com.example.weektimetable.data.dto.WeekDto
import com.example.weektimetable.data.mapper.toEntity
import com.example.weektimetable.data.network.IWeekTimetableNetwork
import com.example.weektimetable.domain.entity.WeekTimetableEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class WeekTimetableRepository(private val network: IWeekTimetableNetwork):IWeekTimeTableRepository {
    override suspend fun getWeekTimetableByGroup(number: String): WeekTimetableEntity {
        return network.getWeekTimetableByGroup(number = number).toEntity()
    }

    override suspend fun getWeekTimetableByAuditory(id: String): WeekTimetableEntity {
        return network.getWeekTimetableByAuditory(id = id).toEntity()
    }

    override suspend fun getWeekTimetableByTeacher(id: String): WeekTimetableEntity {
        return network.getWeekTimetableByTeacher(id = id).toEntity()
    }

}