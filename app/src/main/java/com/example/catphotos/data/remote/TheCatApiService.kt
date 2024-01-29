package com.example.catphotos.data.remote

import com.example.catphotos.data.dto.TheCatApiDTOItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheCatApiService {

    @GET("images/search?")
    suspend fun getImagesCats(
       @Query("limit") limit: Int
    ) : Response<List<TheCatApiDTOItem>>

}