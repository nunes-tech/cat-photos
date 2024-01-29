package com.example.catphotos.data.repository

import com.example.catphotos.data.dto.TheCatApiDTOItem
import com.example.catphotos.data.remote.TheCatApiService
import javax.inject.Inject

class GatosApiRepositoryImpl @Inject constructor(
    private val theCatApiService : TheCatApiService
) : GatosApiRepository {

    override suspend fun getImagesGatosApi(): List<TheCatApiDTOItem> {

       try {
            val response = theCatApiService.getImagesCats(10)
           return if (response.isSuccessful) {
               val body = response.body()
               if (!body.isNullOrEmpty()) {
                   body
               } else {
                   emptyList()
               }
           } else {
               emptyList()
           }
        } catch (erro:Exception) {
            erro.printStackTrace()
           return emptyList()
        }

    }

}