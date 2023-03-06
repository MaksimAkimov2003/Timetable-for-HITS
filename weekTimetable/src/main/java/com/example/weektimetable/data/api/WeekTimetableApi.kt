package com.example.weektimetable.data.api

import com.example.weektimetable.data.dto.WeekDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WeekTimetableApi {

    @GET("api/schedule/group/{number}")
    suspend fun getWeekTimetableByGroup(@Path("number") number: String): WeekDto

    @GET("api/schedule/auditory/{id}")
    suspend fun getWeekTimetableByAuditory(@Path("id") id: String): WeekDto

    @GET("api/schedule/teacher/{id}")
    suspend fun getWeekTimetableByTeacher(@Path("id") id: String): WeekDto

}