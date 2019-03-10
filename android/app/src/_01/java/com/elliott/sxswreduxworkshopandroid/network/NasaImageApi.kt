package com.elliott.sxswreduxworkshopandroid.network

import com.elliott.sxswreduxworkshopandroid.network.model.RootModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaImageApi {
    @GET("search")
    fun searchImages(@Query("q") searchTerm: String): Call<RootModel>
}