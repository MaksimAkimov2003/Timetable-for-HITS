package com.example.weektimetable.data

data class TimeSlotDto (
    val slotNumber: Int,
    val pairs: List<PairDto>?
)