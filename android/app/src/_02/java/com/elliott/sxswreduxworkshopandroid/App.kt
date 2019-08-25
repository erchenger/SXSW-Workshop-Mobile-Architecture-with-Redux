package com.elliott.sxswreduxworkshopandroid

import android.app.Application
import com.elliott.sxswreduxworkshopandroid.network.NasaImageApi
import com.elliott.sxswreduxworkshopandroid.redux.AppReducer
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import org.rekotlin.Store
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class App : Application() {

    open lateinit var store: Store<AppState>

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://images-api.nasa.gov/")
                .build()
        val nasaImageApi = retrofit.create(NasaImageApi::class.java)
        store = Store(AppReducer(), AppState())
    }
}
