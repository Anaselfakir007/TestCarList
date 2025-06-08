package com.carlist.capgemini.repository

import android.os.Debug
import android.util.Log
import com.carlist.capgemini.data.CarDataModel
import com.carlist.capgemini.data.CarRemoteDataModel
import com.carlist.capgemini.mapper.CarsMapper
import com.carlist.capgemini.service.CarsApiService

class CarRepositoryImpl(private val api: CarsApiService) : CarsRepository {
    override suspend fun getCars(make: String): List<CarDataModel> {
        try {
            val response = api.getCars(make = make)
            return response.Models.map { CarsMapper().mapToModel(it) }

        }
        catch (e:Exception){
            Log.e("erreur",e.localizedMessage)
            return emptyList()
        }
     }
}