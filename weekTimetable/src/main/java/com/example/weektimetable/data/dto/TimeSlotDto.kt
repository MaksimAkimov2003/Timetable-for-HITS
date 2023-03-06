package com.example.weektimetable.data.dto

data class TimeSlotDto (
    val slotNumber: Int,
    val pairs: List<PairDto>?
)