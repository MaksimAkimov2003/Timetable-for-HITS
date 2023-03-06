package com.example.weektimetable.domain.usecase

import com.example.weektimetable.domain.entity.WeekTimetableEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class GetWeekTimetableByGroup(private val repository: IWeekTimeTableRepository) {

    suspend operator fun invoke(number: String): WeekTimetableEntity {
        return repository.getWeekTimetableByGroup(number = number)
    }

}