package com.elliott.sxswreduxworkshopandroid.redux

import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollection
import org.rekotlin.StateType

data class AppState(
    val listState: ListState = ListState(),
    val detailAppState: DetailAppState = DetailAppState()
) : StateType

data class ListState(
    val searchTerm: String = "",
    val subtitle: String = "",
    val imageCollection: ImageCollection? = null,
    val isLoading: Boolean = false
)

data class DetailAppState(val selectedItemPosition: Int = -1)