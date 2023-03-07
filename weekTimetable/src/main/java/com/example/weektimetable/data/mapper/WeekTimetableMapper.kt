package com.example.weektimetable.data.mapper

import com.example.weektimetable.data.dto.*
import com.example.weektimetable.domain.entity.*

fun WeekDto.toEntity(): WeekEntity {
    return WeekEntity(
        days = this.days?.map{ it.toEntity() } ?: listOf()
    )
}

fun DayDto.toEntity(): DayEntity {
    return DayEntity(
        weekDay = this.weekDay,
        countClasses = this.countClasses,
        timeSlots = this.timeSlots.map{ it.toEntity() }
    )
}

fun TimeSlotDto.toEntity(): TimeSlotEntity {
    return TimeSlotEntity(
        slotNumber = this.slotNumber,
        pairs = this.pairs?.map{ it.toEntity() } ?: listOf()
    )
}

fun PairDto.toEntity(): PairEntity {
    return PairEntity(
        lessonType = this.lessonType,
        professor = this.professor,
        auditory = this.auditory,
        groups = this.groups.map{ it.toEntity() },
        discipline = this.discipline
    )
}

fun GroupDto.toEntity(): GroupEntity {
    return GroupEntity(
        number = this.number,
        direction = this.direction.toEntity()
    )
}

fun DirectionDto.toEntity(): DirectionEntity {
    return DirectionEntity(
        id = this.id,
        name = this.name,
        number = this.number,
        faculty = this.faculty.toEntity()
    )
}

fun FacultyDto.toEntity(): FacultyEntity {
    return FacultyEntity(
        id = this.id,
        number = this.number,
        name = this.name
    )
}