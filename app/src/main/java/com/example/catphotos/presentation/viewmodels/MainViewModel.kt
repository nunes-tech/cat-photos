package com.example.catphotos.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catphotos.domain.models.UrlImageCat
import com.example.catphotos.domain.usecase.TheCatApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val useCase: TheCatApiUseCase
) : ViewModel() {

    private val _listaGatos = MutableLiveData<List<UrlImageCat>>()
    val listaGatos : LiveData<List<UrlImageCat>> get() = _listaGatos

    suspend fun getImagesTheCatApi() {
        val listImagesGatos = useCase()
        if (!listImagesGatos.isNullOrEmpty()) {
            _listaGatos.postValue( listImagesGatos )
        }
    }

}