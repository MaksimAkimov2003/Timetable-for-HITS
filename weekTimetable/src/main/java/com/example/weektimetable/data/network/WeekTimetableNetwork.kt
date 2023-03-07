package com.example.weektimetable.data.network

import com.example.weektimetable.data.api.WeekTimetableApi
import com.example.weektimetable.data.dto.WeekDateDto
import com.example.weektimetable.data.dto.WeekDto

class WeekTimetableNetwork(private val api: WeekTimetableApi): IWeekTimetableNetwork {
    override suspend fun getWeekTimetableByGroup(number: String, date: WeekDateDto): WeekDto {
        return api.getWeekTimetableByGroup(number = number, date = date)
    }

    override suspend fun getWeekTimetableByAuditory(id: String, date: WeekDateDto): WeekDto {
        return api.getWeekTimetableByAuditory(id = id, date = date)
    }

    override suspend fun getWeekTimetableByTeacher(id: String, date: WeekDateDto): WeekDto {
        return api.getWeekTimetableByTeacher(id = id, date = date)
    }
}