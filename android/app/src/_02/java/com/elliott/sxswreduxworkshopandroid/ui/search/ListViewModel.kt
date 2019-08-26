package com.elliott.sxswreduxworkshopandroid.ui.search

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.elliott.sxswreduxworkshopandroid.App
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import com.elliott.sxswreduxworkshopandroid.redux.SetSearchText
import org.rekotlin.StoreSubscriber
import org.rekotlin.StoreType

class ListViewModel(application: Application) : AndroidViewModel(application), StoreSubscriber<AppState> {

    val uiModelLiveData = MutableLiveData<MainUiModel>()
    private val store: StoreType<AppState> = (application as App).store

    init {
        store.subscribe(this)
    }

    fun onSearch(searchTerm: String) {
        val searchAction = SetSearchText(searchTerm)
        store.dispatch(searchAction)
    }

    override fun newState(state: AppState) {
        val state = store.state
        uiModelLiveData.value = MainUiModel.fromListState(state.listState)
    }

    override fun onCleared() {
        store.unsubscribe(this)
    }
}
