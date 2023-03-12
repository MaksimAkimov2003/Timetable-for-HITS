package com.example.weektimetable.data.api

import com.example.weektimetable.data.dto.WeekDateDto
import com.example.weektimetable.data.dto.WeekDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeekTimetableApi {

    @GET("/api/schedule/group/{number}")
    suspend fun getWeekTimetableByGroup(@Path("number") number: String, @Query("date") date: WeekDateDto): WeekDto

    @GET("/api/schedule/auditory/{id}")
    suspend fun getWeekTimetableByAuditory(@Path("id") id: String, @Query("date") date: WeekDateDto): WeekDto

    @GET("/api/schedule/teacher/{id}")
    suspend fun getWeekTimetableByTeacher(@Path("id") id: String, @Query("date") date: WeekDateDto): WeekDto

}