package com.example.weektimetable.data.mapper

import com.example.weektimetable.data.dto.WeekDto
import com.example.weektimetable.domain.entity.WeekTimetableEntity

fun WeekDto.toEntity(): WeekTimetableEntity {
    return WeekTimetableEntity(1)
}