package com.example.weektimetable.domain.usecase

import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.repository.IWeekTimeTableRepository

class GetWeekTimetableByAuditoryUseCase(private val repository: IWeekTimeTableRepository)  {

    suspend operator fun invoke(id: String): WeekEntity {
        return repository.getWeekTimetableByAuditory(id = id)
    }

}