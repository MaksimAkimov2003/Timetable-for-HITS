package com.example.weektimetable.domain.usecase

import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class GetWeekTimetableByGroupUseCase(private val repository: IWeekTimeTableRepository) {

    suspend operator fun invoke(number: String): WeekEntity {
        return repository.getWeekTimetableByGroup(number = number)
    }

}