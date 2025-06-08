package com.carlist.capgemini.data

class CarRemoteDataModel(
val model_name: String?,
val model_make_id: String?,
val model_trim: String?,
val model_year: String?
)

data class CarApiResponse(val Models: List<CarRemoteDataModel>)