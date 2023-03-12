package com.example.weektimetable.domain.entity

data class TimeSlotEntity (
    val slotNumber: Int,
    val pairs: List<PairEntity>?
)