package com.carlist.capgemini.mapper

import com.carlist.capgemini.data.CarDataModel
import com.carlist.capgemini.data.CarRemoteDataModel

class CarsMapper {
    fun mapToModel(model: CarRemoteDataModel):CarDataModel = CarDataModel(    modelName = model.model_name.orEmpty(),
        description = "Model year: ${model.model_make_id ?: "N/A"}",
        // Pas d’image dans API, on génère une URL image exemple (placeholder)
        imageUrl = "https://via.placeholder.com/150?text=${model.model_name.orEmpty().replace(" ", "+")}"
    )
}