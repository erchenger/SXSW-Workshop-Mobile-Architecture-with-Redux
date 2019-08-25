package com.elliott.sxswreduxworkshopandroid.redux

import org.rekotlin.Action
import org.rekotlin.Reducer

class AppReducer : Reducer<AppState> {

    override fun invoke(action: Action, state: AppState?): AppState {
        var state = state ?: AppState()
        return when (action) {
            is ListStateReducer -> {
                val listState = action.reduce(state.listState)
                state.copy(listState = listState)
            }
            else -> state
        }
    }
}
