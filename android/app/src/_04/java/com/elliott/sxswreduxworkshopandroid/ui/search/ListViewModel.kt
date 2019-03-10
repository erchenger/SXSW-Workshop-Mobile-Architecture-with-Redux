package com.elliott.sxswreduxworkshopandroid.ui.search

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.elliott.sxswreduxworkshopandroid.App
import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollectionItem
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import com.elliott.sxswreduxworkshopandroid.redux.ExecuteSearch
import com.elliott.sxswreduxworkshopandroid.redux.SetSearchText
import redux.api.Store

class ListViewModel(application: Application) : AndroidViewModel(application) {

    val uiModelLiveData = MutableLiveData<MainUiModel>()
    var subscription: Store.Subscription? = null
    private val store: Store<AppState> = (application as App).store
    var lastList: List<ImageCollectionItem>? = null

    init {
        subscription = store.subscribe {
            val state = store.state
            lastList = state.listState.imageCollection?.items
            uiModelLiveData.value = MainUiModel.fromListState(application, state.listState)
        }
    }

    fun onSearch(searchTerm: String) {
        val searchAction = SetSearchText(searchTerm)
        store.dispatch(searchAction)
    }

    override fun onCleared() {
        subscription?.unsubscribe()
    }

    fun submitPressed() {
        store.dispatch(ExecuteSearch())
    }
}
