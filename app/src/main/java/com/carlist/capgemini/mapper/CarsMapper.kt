package com.carlist.capgemini.mapper

import com.carlist.capgemini.data.CarDataModel
import com.carlist.capgemini.data.CarRemoteDataModel

class CarsMapper {
    fun mapToModel(model: CarRemoteDataModel):CarDataModel = CarDataModel(    modelName = model.model_name.orEmpty(),
        description = "Model : ${model.model_make_id ?: "N/A"}",
        //CANNOT GET IMAGE URL FROM THE FREE VERSION OF Carqueryapi i just used a static url
        imageUrl = "https://www.wandaloo.com/files/2011/11/AUDI-A1-Sportback-officiel.jpg"
    )
}