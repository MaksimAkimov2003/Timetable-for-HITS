package com.example.weektimetable.domain.usecase

import com.example.weektimetable.domain.entity.WeekTimetableEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class GetWeekTimetableByTeacher(private val repository: IWeekTimeTableRepository) {

    suspend operator fun invoke(id: String): WeekTimetableEntity {
        return repository.getWeekTimetableByTeacher(id = id)
    }

}