package com.example.catphotos.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catphotos.domain.models.UrlImageCat
import com.example.catphotos.domain.usecase.TheCatApiUseCaseImpl
import com.example.meusgatinhos.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCaseImpl: TheCatApiUseCaseImpl
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        val instanceTaskExecutorRule =
            MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel( useCaseImpl )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getImagesTheCatApi_validaValiveData_retornaLiveData() = runTest {
        Mockito.`when`( useCaseImpl() ).thenReturn(
            listOf(
                UrlImageCat("https://cdn2.thecatapi.com/images/4nc.jpg"),
                UrlImageCat("https://cdn2.thecatapi.com/images/7iq.jpg"),
                UrlImageCat("https://cdn2.thecatapi.com/images/93s.jpg"),
                UrlImageCat("https://cdn2.thecatapi.com/images/a27.jpg")
            )
        )

        mainViewModel.getImagesTheCatApi()
        val liveData = mainViewModel.listaGatos.getOrAwaitValue()

        assertThat( liveData ).isNotEmpty()
    }
}