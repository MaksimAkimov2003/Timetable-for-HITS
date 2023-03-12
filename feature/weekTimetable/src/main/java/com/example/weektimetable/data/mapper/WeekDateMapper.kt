package com.example.weektimetable.data.mapper

import com.example.weektimetable.data.dto.WeekDateDto
import com.example.weektimetable.domain.entity.WeekDateEntity
import java.util.*

fun WeekDateEntity.toDto(): WeekDateDto {
    val start = Date(this.startDate)
    val end = Date(this.endDate)
    return WeekDateDto(
        startDate = start.toString(),
        endDate = end.toString()
    )
}