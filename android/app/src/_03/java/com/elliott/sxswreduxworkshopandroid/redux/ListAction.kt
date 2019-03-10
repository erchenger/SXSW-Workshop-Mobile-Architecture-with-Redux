package com.elliott.sxswreduxworkshopandroid.redux

import redux.api.Action
import java.io.Serializable

abstract class ListAction : Action

interface ListStateReducer {
    fun reduce(state: ListState): ListState
}

data class SetSearchText(val searchTerm: String) : ListAction(), ListStateReducer {
    override fun getType(): Serializable {
        return -1
    }

    override fun reduce(state: ListState): ListState {
        return state.copy(searchTerm = searchTerm)
    }
}
