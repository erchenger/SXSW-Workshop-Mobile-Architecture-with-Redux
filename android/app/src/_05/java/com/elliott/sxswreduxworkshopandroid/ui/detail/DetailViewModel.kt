package com.elliott.sxswreduxworkshopandroid.ui.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.elliott.sxswreduxworkshopandroid.App
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import org.rekotlin.Store

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val uiModelLiveData = MutableLiveData<DetailUiModel>()
    private val store: Store<AppState> = (application as App).store

    init {
        val state = store.state
        state.listState.imageCollection?.items?.let {
            val position = state.detailAppState.selectedItemPosition
            val uiModel = DetailUiModel(it[position])
            uiModelLiveData.value = uiModel
        }
    }
}