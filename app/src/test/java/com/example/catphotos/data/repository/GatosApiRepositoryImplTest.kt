package com.example.catphotos.data.repository

import com.example.catphotos.data.remote.TheCatApiInterceptor
import com.example.catphotos.data.remote.TheCatApiService
import com.example.catphotos.utils.Constants
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GatosApiRepositoryImplTest {
    //Teste Real do RepositoryApi
    private lateinit var gatosApiRepository: GatosApiRepositoryImpl

    @Before fun setUp() {
        val theCatApiService = getInstanceApiService()
        gatosApiRepository = GatosApiRepositoryImpl( theCatApiService )
    }

    @After fun tearDown() {  }

    @Test
    fun getImagesGatosApi_retornaListaUrlsDaApi_retornaListaPreenchida() = runTest {
        val lista = gatosApiRepository.getImagesGatosApi()

        assertThat(lista).isNotEmpty()
    }

    private fun getInstanceApiService(): TheCatApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TheCatApiInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.baseUrlTheCatApi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TheCatApiService::class.java)
    }

}