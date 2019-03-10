package com.elliott.sxswreduxworkshopandroid.redux

import redux.api.Action
import java.io.Serializable

abstract class ListAction : Action

interface ListStateReducer {
    fun reduce(state: ListState): ListState
}
