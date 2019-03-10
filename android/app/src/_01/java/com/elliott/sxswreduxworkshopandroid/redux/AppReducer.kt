package com.elliott.sxswreduxworkshopandroid.redux

import redux.api.Action
import redux.api.Reducer

class AppReducer : Reducer<AppState> {

    override fun reduce(state: AppState, action: Action?): AppState {
        return when (action) {
            is ListStateReducer -> {
                val listState = action.reduce(state.listState)
                state.copy(listState = listState)
            }
            else -> state
        }
    }
}
