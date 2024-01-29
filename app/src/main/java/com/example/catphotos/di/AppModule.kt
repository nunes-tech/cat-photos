package com.example.catphotos.di

import com.example.catphotos.data.remote.TheCatApiInterceptor
import com.example.catphotos.data.remote.TheCatApiService
import com.example.catphotos.data.repository.GatosApiRepository
import com.example.catphotos.data.repository.GatosApiRepositoryImpl
import com.example.catphotos.domain.usecase.TheCatApiUseCase
import com.example.catphotos.domain.usecase.TheCatApiUseCaseImpl
import com.example.catphotos.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun proverTheCatApiInterceptor() : TheCatApiInterceptor {
        return TheCatApiInterceptor()
    }

    @Provides
    fun proverOkHTTPclient(
        interceptor: TheCatApiInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun proverRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.baseUrlTheCatApi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun proverServiceTheCatApi(
        retrofit: Retrofit
    ) : TheCatApiService {
        return retrofit.create(TheCatApiService::class.java)
    }

    @Provides
    fun proverGatosApiRepository(
        theCatApi: TheCatApiService
    ) : GatosApiRepository {
        return GatosApiRepositoryImpl( theCatApi )
    }

    @Provides
    fun proverTheCatApiUseCase(
        repository: GatosApiRepository
    ) : TheCatApiUseCase {
        return TheCatApiUseCaseImpl( repository )
    }

}