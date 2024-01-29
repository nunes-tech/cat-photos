package com.example.catphotos.domain.usecase

import com.example.catphotos.data.dto.TheCatApiDTOItem
import com.example.catphotos.data.repository.GatosApiRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith( MockitoJUnitRunner::class)
class TheCatApiUseCaseImplTest {

    @Mock
    private lateinit var gatosApiRepository: GatosApiRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun invoke_retornaListaDeUrls_retornaLista() = runTest {
        val theCatApiUseCase = TheCatApiUseCaseImpl(gatosApiRepository)

        //QUANDO
        Mockito.`when`( gatosApiRepository.getImagesGatosApi() ).thenReturn(
            listOf(
                TheCatApiDTOItem(
                    listOf("Pitbull", "Pastor Alemão"),
                    400,"f20", "https://gatos.com.br/image1.jpj",500
                ),
                TheCatApiDTOItem(
                    listOf("Rotwailler", "Lavrador"),
                    600,"q28", "https://gatos.com.br/image2.jpj",400
                )
            )
        )

        //DADO ==============================
        val lista = theCatApiUseCase()

        //ENTÃO ===========================
        assertThat(lista).isNotEmpty()
    }

    @After
    fun tearDown() {
    }
}