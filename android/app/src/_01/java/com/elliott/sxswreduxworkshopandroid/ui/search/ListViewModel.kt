package com.elliott.sxswreduxworkshopandroid.ui.search

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.elliott.sxswreduxworkshopandroid.App
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import redux.api.Store

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val uiModelLiveData = MutableLiveData<MainUiModel>()
    var subscription: Store.Subscription? = null
    private val store: Store<AppState> = (application as App).store

    init {
        subscription = store.subscribe {
            val state = store.state
        }
    }

    override fun onCleared() {
        subscription?.unsubscribe()
    }
}