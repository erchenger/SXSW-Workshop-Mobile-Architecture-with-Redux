package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.network.NasaImageApi
import com.elliott.sxswreduxworkshopandroid.network.model.*
import com.elliott.sxswreduxworkshopandroid.redux.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ErrorCollector
import org.mockito.Mockito.mock
import org.rekotlin.Store

class ListActionUnitTests{

    @get:Rule
    val collector = ErrorCollector()

    private lateinit var store: Store<AppState>
    private lateinit var nasaImageApi: NasaImageApi

    @Before
    fun setUp() {
        nasaImageApi = mock(NasaImageApi::class.java)
        store = Store(AppReducer(), AppState())
    }


    inline fun <reified T : Any> mockGeneric() = mock(T::class.java)

    private fun getTestData(value: Int): Pair<ImageCollection, RootModel> {
        val imageLinks = listOf(ImageCollectionLink("https://example.com/$value"))
        val imageDataList = listOf(ImageCollectionItemData("Sample description $value", "Sample Title $value"))
        val image = ImageCollectionItem(imageDataList, "https://example.com/$value", imageLinks)
        val imageItemList = listOf(image)
        val imageCollection = ImageCollection("", imageItemList)
        val rootModel = RootModel(imageCollection)
        return Pair(imageCollection, rootModel)
    }

}
