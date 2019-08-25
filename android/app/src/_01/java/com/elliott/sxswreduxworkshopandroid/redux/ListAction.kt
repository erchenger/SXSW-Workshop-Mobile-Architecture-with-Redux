package com.elliott.sxswreduxworkshopandroid.redux

import org.rekotlin.Action

abstract class ListAction : Action

interface ListStateReducer {
    fun reduce(state: ListState): ListState
}
