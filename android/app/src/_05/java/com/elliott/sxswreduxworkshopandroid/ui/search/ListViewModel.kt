package com.elliott.sxswreduxworkshopandroid.ui.search

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.elliott.sxswreduxworkshopandroid.App
import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollectionItem
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import com.elliott.sxswreduxworkshopandroid.redux.ExecuteSearch
import com.elliott.sxswreduxworkshopandroid.redux.ImageSelectionAction
import com.elliott.sxswreduxworkshopandroid.redux.SetSearchText
import org.rekotlin.Store
import org.rekotlin.StoreSubscriber

class ListViewModel(application: Application) : AndroidViewModel(application), StoreSubscriber<AppState> {

    val uiModelLiveData = MutableLiveData<MainUiModel>()
    private val store: Store<AppState> = (application as App).store
    var lastList: List<ImageCollectionItem>? = null

    init {
        store.subscribe(this)
    }

    fun onSearch(searchTerm: String) {
        val searchAction = SetSearchText(searchTerm)
        store.dispatch(searchAction)
        store.dispatch(ExecuteSearch())
    }

    fun itemSelected(position: Int) {
        val itemSelectionAction = ImageSelectionAction(position)
        store.dispatch(itemSelectionAction)
    }

    override fun onCleared() {
        store.unsubscribe(this)
    }

    fun submitPressed() {
        store.dispatch(ExecuteSearch())
    }


    override fun newState(state: AppState) {
        val state = store.state
        lastList = state.listState.imageCollection?.items
        uiModelLiveData.value = MainUiModel.fromListState(getApplication(), state.listState)
    }
}