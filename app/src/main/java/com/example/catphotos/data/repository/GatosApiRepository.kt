package com.example.catphotos.data.repository

import com.example.catphotos.data.dto.TheCatApiDTOItem


interface GatosApiRepository {

    suspend fun getImagesGatosApi() : List<TheCatApiDTOItem>

}