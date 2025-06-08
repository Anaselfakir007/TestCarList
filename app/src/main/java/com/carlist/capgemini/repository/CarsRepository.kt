package com.carlist.capgemini.repository

import com.carlist.capgemini.data.CarDataModel
import com.carlist.capgemini.data.CarRemoteDataModel

interface CarsRepository {
    suspend fun getCars(make: String): List<CarDataModel>
}