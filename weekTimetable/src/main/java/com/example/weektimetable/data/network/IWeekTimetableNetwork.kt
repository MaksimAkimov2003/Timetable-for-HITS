package com.example.weektimetable.data.network

import com.example.weektimetable.data.dto.WeekDateDto
import com.example.weektimetable.data.dto.WeekDto

interface IWeekTimetableNetwork {
    suspend fun getWeekTimetableByGroup(number: String, date: WeekDateDto): WeekDto
    suspend fun getWeekTimetableByAuditory(id: String, date: WeekDateDto): WeekDto
    suspend fun getWeekTimetableByTeacher(id: String, date: WeekDateDto): WeekDto
}