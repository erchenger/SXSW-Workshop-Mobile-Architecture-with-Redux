package com.elliott.sxswreduxworkshopandroid.redux

import org.rekotlin.Action

abstract class ListAction : Action

interface ListStateReducer {
    fun reduce(state: ListState): ListState
}

data class SetSearchText(val searchTerm: String) : ListAction(), ListStateReducer {
    override fun reduce(state: ListState): ListState {
        return state.copy(searchTerm = searchTerm)
    }
}
