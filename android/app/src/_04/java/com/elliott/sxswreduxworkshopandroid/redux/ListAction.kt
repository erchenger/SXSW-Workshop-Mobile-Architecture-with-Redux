package com.elliott.sxswreduxworkshopandroid.redux

import com.elliott.sxswreduxworkshopandroid.network.NasaImageApi
import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollection
import com.elliott.sxswreduxworkshopandroid.network.model.RootModel
import com.elliott.sxswreduxworkshopandroid.redux.middleware.AsyncActionable
import org.rekotlin.Action
import org.rekotlin.DispatchFunction
import org.rekotlin.Store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class ListAction : Action

interface ListStateReducer {
    fun reduce(state: ListState): ListState
}

data class SetSearchText(val searchTerm: String) : ListAction(), ListStateReducer {

    override fun reduce(state: ListState): ListState {
        return state.copy(searchTerm = searchTerm)
    }

}

class ExecuteSearch : ListAction(), AsyncActionable, ListStateReducer {

    override fun reduce(state: ListState): ListState {
        return state.copy(isLoading = true)
    }

    override fun postReduce(
        dispatchFunction: DispatchFunction,
        state: AppState?,
        imageApi: NasaImageApi
    ) {
        state?.let {
            val imageSearchCall = imageApi.searchImages(state.listState.searchTerm)
            imageSearchCall.enqueue(object : Callback<RootModel> {
                override fun onFailure(call: Call<RootModel>, t: Throwable) {}

                override fun onResponse(call: Call<RootModel>, response: Response<RootModel>) {
                    response.body()?.let {
                        val responseAction = ImageSearchResponseAction(it.collection)
                        dispatchFunction(responseAction)
                    }
                }
            })
        }
    }
}


data class ImageSearchResponseAction(val imageCollection: ImageCollection) : ListAction(),
    ListStateReducer {

    override fun reduce(state: ListState): ListState {
        return state.copy(
            searchTerm = state.searchTerm,
            isLoading = false,
            subtitle = "",
            imageCollection = imageCollection
        )
    }
}
