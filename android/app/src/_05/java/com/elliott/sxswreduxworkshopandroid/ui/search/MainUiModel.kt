package com.elliott.sxswreduxworkshopandroid.ui.search

import android.app.Application
import android.support.annotation.VisibleForTesting
import com.elliott.sxswreduxworkshopandroid.R
import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollectionItem
import com.elliott.sxswreduxworkshopandroid.redux.ListState

data class MainUiModel private constructor(val searchSubTitle: String = "",
                                           val images: List<ImageCollectionItem>? = emptyList(),
                                           val noItemsMessage: String? = null,
                                           val isLoading: Boolean = false) {

    companion object {
        fun fromListState(application: Application, listState: ListState): MainUiModel {
            val (list, message) = if (listState.imageCollection?.items?.isNullOrEmpty() == true) Pair(emptyList(), application.getString(R.string.no_items_label)) else Pair(listState.imageCollection?.items, "")
            return MainUiModel(getValidationFromState(listState), list, message, listState.isLoading)
        }

        @VisibleForTesting
        fun getValidationFromState(state: ListState): String {
            return when {
                state.searchTerm.length in 1..1 -> "Enter at least two characters"
                state.searchTerm.equals("earth", true) -> "Home Sweet Home"
                state.searchTerm.equals("mars", true) -> "The Red Planet"
                state.searchTerm.equals("pluto", true) -> "Formerly the 9th planet"
                else -> ""
            }
        }

    }
}
