package com.example.catphotos.domain.usecase

import com.example.catphotos.data.repository.GatosApiRepository
import com.example.catphotos.domain.models.UrlImageCat
import javax.inject.Inject

class TheCatApiUseCaseImpl @Inject constructor(
    private val repository : GatosApiRepository
) : TheCatApiUseCase {

    override suspend operator fun invoke() : List<UrlImageCat> {
        val listaCatsDTO = repository.getImagesGatosApi()
        val listUrlImages = mutableListOf<UrlImageCat>()

        listaCatsDTO.forEach { item ->
            listUrlImages.add(
                UrlImageCat( item.url )
            )
        }

        return listUrlImages
    }

}