package com.carlist.capgemini.usecase

import com.carlist.capgemini.repository.CarsRepository

class CarsUseCase(private val repo: CarsRepository) {

    suspend operator fun invoke(modelName: String) = repo.getCars(modelName)
}