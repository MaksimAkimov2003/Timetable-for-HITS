package com.example.weektimetable.data.dto

import com.google.gson.annotations.SerializedName

data class TimeSlotDto (
    @SerializedName("SlotNumber")
    val slotNumber: Int,
    @SerializedName("Pairs")
    val pairs: List<PairDto>?
)