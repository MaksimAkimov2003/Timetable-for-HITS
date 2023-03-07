package com.example.weektimetable.data.repository

import com.example.weektimetable.data.mapper.toDto
import com.example.weektimetable.data.mapper.toEntity
import com.example.weektimetable.data.network.IWeekTimetableNetwork
import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class WeekTimetableRepository(private val network: IWeekTimetableNetwork):IWeekTimeTableRepository {
    override suspend fun getWeekTimetableByGroup(number: String, date: WeekDateEntity): WeekEntity {
        return network.getWeekTimetableByGroup(number = number, date = date.toDto()).toEntity()
    }

    override suspend fun getWeekTimetableByAuditory(id: String, date: WeekDateEntity): WeekEntity {
        return network.getWeekTimetableByAuditory(id = id, date = date.toDto()).toEntity()
    }

    override suspend fun getWeekTimetableByTeacher(id: String, date: WeekDateEntity): WeekEntity {
        return network.getWeekTimetableByTeacher(id = id, date = date.toDto()).toEntity()
    }

}