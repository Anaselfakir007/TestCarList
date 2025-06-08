package com.carlist.capgemini.service

import com.carlist.capgemini.data.CarApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CarsApiService {
    @GET("?cmd=getModels")
    suspend fun getCars(
        @Query("make") make: String
    ): CarApiResponse
}
