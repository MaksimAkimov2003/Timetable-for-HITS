package com.example.weektimetable.data.repository

import com.example.weektimetable.data.mapper.toEntity
import com.example.weektimetable.data.network.IWeekTimetableNetwork
import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class WeekTimetableRepository(private val network: IWeekTimetableNetwork):IWeekTimeTableRepository {
    override suspend fun getWeekTimetableByGroup(number: String): WeekEntity {
        return network.getWeekTimetableByGroup(number = number).toEntity()
    }

    override suspend fun getWeekTimetableByAuditory(id: String): WeekEntity {
        return network.getWeekTimetableByAuditory(id = id).toEntity()
    }

    override suspend fun getWeekTimetableByTeacher(id: String): WeekEntity {
        return network.getWeekTimetableByTeacher(id = id).toEntity()
    }

}