package com.elliott.sxswreduxworkshopandroid.ui.search

import com.elliott.sxswreduxworkshopandroid.network.model.ImageCollectionItem
import com.elliott.sxswreduxworkshopandroid.redux.ListState

data class MainUiModel private constructor(val searchSubTitle: String = "",
                                           val images: List<ImageCollectionItem> = emptyList(),
                                           val noItemsMessage: String? = null,
                                           val isLoading: Boolean = false) {

    companion object {

        fun fromListState(listState: ListState): MainUiModel? {
            return MainUiModel()
        }
    }
}