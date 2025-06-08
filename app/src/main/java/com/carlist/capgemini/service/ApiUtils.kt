package com.carlist.capgemini.service

import com.carlist.capgemini.CarsViewModel
import com.carlist.capgemini.repository.CarRepositoryImpl
import com.carlist.capgemini.repository.CarsRepository
import com.carlist.capgemini.usecase.CarsUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



//faute de temps je n'es pas utilisé Injection de dependance (Koin par exemple) j'ai juste utiliser Singleton Object
object ApiUtils {


    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.carqueryapi.com/api/0.3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val api: CarsApiService = retrofit.create(CarsApiService::class.java)

    // Repository
    val repository: CarsRepository = CarRepositoryImpl(api)

    // UseCase
    val carsUseCase = CarsUseCase(repository)

    // ViewModel
    val viewModel = CarsViewModel(carsUseCase)
}
