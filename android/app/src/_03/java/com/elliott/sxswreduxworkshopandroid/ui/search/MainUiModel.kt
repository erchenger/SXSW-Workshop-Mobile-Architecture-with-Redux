package com.elliott.sxswreduxworkshopandroid.ui.search

import android.support.annotation.VisibleForTesting
import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollectionItem
import com.elliott.sxswreduxworkshopandroid.redux.ListState

data class MainUiModel private constructor(val searchSubTitle: String = "",
                                           val images: List<ImageCollectionItem> = emptyList(),
                                           val noItemsMessage: String? = null,
                                           val isLoading: Boolean = false) {

    companion object {

        fun fromListState(listState: ListState): MainUiModel? {
            return MainUiModel(searchSubTitle = getValidationFromState(listState))
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
