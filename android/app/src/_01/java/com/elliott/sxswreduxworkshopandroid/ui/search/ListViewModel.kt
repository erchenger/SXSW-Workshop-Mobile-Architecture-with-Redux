package com.elliott.sxswreduxworkshopandroid.ui.search

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.elliott.sxswreduxworkshopandroid.App
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber

class ListViewModel(application: Application) : AndroidViewModel(application), StoreSubscriber<AppState> {

    val uiModelLiveData = MutableLiveData<MainUiModel>()
    private val store: Store<AppState> = (application as App).store

    init {
        store.subscribe(this)
    }

    override fun newState(state: AppState) {
    }

    override fun onCleared() {
        store.unsubscribe(this)
    }
}
