package com.elliott.sxswreduxworkshopandroid.redux.middleware

import com.elliott.sxswreduxworkshopandroid.network.NasaImageApi
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import redux.api.Action
import redux.api.Dispatcher
import redux.api.Store
import redux.api.enhancer.Middleware

class AsyncMiddleware(private val imageApi: NasaImageApi) : Middleware<AppState> {
    override fun dispatch(store: Store<AppState>, next: Dispatcher, action: Action): Action {
        val nextAction = next.dispatch(action)

        when (action) {
            is AsyncActionable -> {
                action.postReduce(store, imageApi)
            }
        }
        return nextAction
    }
}

interface AsyncActionable : Action {
    fun postReduce(store: Store<AppState>, imageApi: NasaImageApi)
}