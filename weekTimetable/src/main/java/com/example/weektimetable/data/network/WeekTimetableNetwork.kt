package com.example.weektimetable.data.network

import com.example.weektimetable.data.api.WeekTimetableApi
import com.example.weektimetable.data.dto.WeekDto

class WeekTimetableNetwork(private val api: WeekTimetableApi): IWeekTimetableNetwork {
    override suspend fun getWeekTimetableByGroup(number: String): WeekDto {
        return api.getWeekTimetableByGroup(number = number)
    }

    override suspend fun getWeekTimetableByAuditory(id: String): WeekDto {
        return api.getWeekTimetableByAuditory(id = id)
    }

    override suspend fun getWeekTimetableByTeacher(id: String): WeekDto {
        return api.getWeekTimetableByTeacher(id = id)
    }
}