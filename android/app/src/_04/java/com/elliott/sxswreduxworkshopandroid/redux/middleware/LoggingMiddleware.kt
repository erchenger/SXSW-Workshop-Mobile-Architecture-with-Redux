package com.elliott.sxswreduxworkshopandroid.redux.middleware

import android.util.Log
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import redux.api.Action
import redux.api.Dispatcher
import redux.api.Store
import redux.api.enhancer.Middleware

class LoggingMiddleware : Middleware<AppState> {
    override fun dispatch(store: Store<AppState>, next: Dispatcher, action: Action): Action {

        Log.d("APP", "Dispatching $action")
        Log.d("APP", "SearchTeam before reducer ${store.state.listState.searchTerm}")
        val nextAction = next.dispatch(action)
        Log.d("APP", "SearchTeam after reducer ${store.state.listState.searchTerm}")
        return nextAction
    }
}