package com.example.weektimetable.data.mapper

import com.example.weektimetable.data.dto.WeekDateDto
import com.example.weektimetable.domain.entity.WeekDateEntity

fun WeekDateEntity.toDto(): WeekDateDto {
    return WeekDateDto(
        startDate = this.startDate,
        endDate = this.endDate
    )
}