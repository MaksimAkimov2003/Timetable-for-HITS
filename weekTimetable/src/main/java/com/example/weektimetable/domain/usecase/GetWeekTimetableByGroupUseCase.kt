package com.example.weektimetable.domain.usecase

import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class GetWeekTimetableByGroupUseCase(private val repository: IWeekTimeTableRepository) {

    suspend operator fun invoke(number: String, date: WeekDateEntity): WeekEntity {
        return repository.getWeekTimetableByGroup(number = number, date = date)
    }

}