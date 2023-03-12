package com.example.weektimetable.domain.usecase

import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class GetWeekTimetableByAuditoryUseCase(private val repository: IWeekTimeTableRepository)  {

    suspend operator fun invoke(id: String, date: WeekDateEntity): WeekEntity {
        return repository.getWeekTimetableByAuditory(id = id, date = date)
    }

}