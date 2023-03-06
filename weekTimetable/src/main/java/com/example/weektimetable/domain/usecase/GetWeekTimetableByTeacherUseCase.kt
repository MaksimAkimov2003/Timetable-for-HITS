package com.example.weektimetable.domain.usecase

import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class GetWeekTimetableByTeacherUseCase(private val repository: IWeekTimeTableRepository) {

    suspend operator fun invoke(id: String): WeekEntity {
        return repository.getWeekTimetableByTeacher(id = id)
    }

}