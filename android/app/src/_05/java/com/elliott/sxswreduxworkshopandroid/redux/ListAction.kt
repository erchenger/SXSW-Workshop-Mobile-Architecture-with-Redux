package com.elliott.sxswreduxworkshopandroid.redux

import com.elliott.sxswreduxworkshopandroid.network.NasaImageApi
import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollection
import com.elliott.sxswreduxworkshopandroid.network.model.RootModel
import com.elliott.sxswreduxworkshopandroid.redux.middleware.AsyncActionable
import redux.api.Action
import redux.api.Store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

abstract class ListAction : Action

interface ListStateReducer {
    fun reduce(state: ListState): ListState
}

interface DetailStateReducer {
    fun reduce(state: DetailAppState): DetailAppState
}

data class SetSearchText(val searchTerm: String) : ListAction(), ListStateReducer {
    override fun getType(): Serializable {
        return -1
    }

    override fun reduce(state: ListState): ListState {
        return state.copy(searchTerm = searchTerm)
    }

}

class ExecuteSearch : ListAction(), AsyncActionable, ListStateReducer {
    override fun getType(): Serializable {
        return -5
    }

    override fun reduce(state: ListState): ListState {
        return state.copy(isLoading = true)
    }

    override fun postReduce(store: Store<AppState>, imageApi: NasaImageApi) {
        if (store.state.listState.searchTerm.isEmpty()) {
            val blankImageCollection = ImageCollection("", emptyList())
            val responseAction = ImageSearchResponseAction(blankImageCollection)
            store.dispatch(responseAction)
        } else {
            val requestSearchTerm = store.state.listState.searchTerm
            val imageSearchCall = imageApi.searchImages(requestSearchTerm)
            imageSearchCall.enqueue(object : Callback<RootModel> {
                override fun onFailure(call: Call<RootModel>, t: Throwable) {}

                override fun onResponse(call: Call<RootModel>, response: Response<RootModel>) {
                    if (store.state.listState.searchTerm == requestSearchTerm) {
                        response.body()?.let {
                            val responseAction = ImageSearchResponseAction(it.collection)
                            store.dispatch(responseAction)
                        }
                    }
                }
            })
        }
    }
}


data class ImageSearchResponseAction(val imageCollection: ImageCollection) : ListAction(), ListStateReducer {
    override fun getType(): Serializable {
        return -2
    }

    override fun reduce(state: ListState): ListState {
        return state.copy(
            searchTerm = state.searchTerm,
            isLoading = false,
            subtitle = "",
            imageCollection = imageCollection
        )
    }
}

data class ImageSelectionAction(val position: Int) : ListAction(), DetailStateReducer {
    override fun getType(): Serializable {
        return -3
    }

    override fun reduce(state: DetailAppState): DetailAppState {
        return state.copy(selectedItemPosition = position)
    }
}
