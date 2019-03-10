package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.network.model.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ErrorCollector
import org.mockito.Mockito

class MainUiModelUnitTests {

    @get:Rule
    val collector = ErrorCollector()

    private lateinit var mockApplication: App

    @Before
    fun setUp() {
        mockApplication = Mockito.mock(App::class.java)
    }

    private fun getTestData(): Pair<ImageCollection, RootModel> {
        val imageLinks = listOf(ImageCollectionLink("https://example.com"))
        val imageDataList = listOf(ImageCollectionItemData("Sample description", "Sample Title"))
        val image = ImageCollectionItem(imageDataList, "https://example.com", imageLinks)
        val imageItemList = listOf(image)
        val imageCollection = ImageCollection("", imageItemList)
        val rootModel = RootModel(imageCollection)
        return Pair(imageCollection, rootModel)
    }

}