package com.example.catphotos.domain.usecase

import com.example.catphotos.domain.models.UrlImageCat


interface TheCatApiUseCase {
    suspend operator fun invoke() : List<UrlImageCat>
}