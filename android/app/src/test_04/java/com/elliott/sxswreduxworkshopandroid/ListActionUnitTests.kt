package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.network.NasaImageApi
import com.elliott.sxswreduxworkshopandroid.network.model.*
import com.elliott.sxswreduxworkshopandroid.redux.AppReducer
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import com.elliott.sxswreduxworkshopandroid.redux.ExecuteSearch
import com.elliott.sxswreduxworkshopandroid.redux.SetSearchText
import com.elliott.sxswreduxworkshopandroid.redux.middleware.AsyncMiddleware
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ErrorCollector
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import redux.Redux
import redux.api.Store
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActionUnitTests {

    @get:Rule
    val collector = ErrorCollector()

    private lateinit var enhancers: Store.Enhancer<AppState>
    private lateinit var store: Store<AppState>
    private lateinit var nasaImageApi: NasaImageApi

    @Before
    fun setUp() {
        nasaImageApi = mock(NasaImageApi::class.java)
        enhancers = Redux.applyMiddleware(AsyncMiddleware(nasaImageApi))
        store = Redux.createStore(AppReducer(), AppState(), enhancers)
    }

    @Test
    fun executeSearch_success() {
        //Arrange
        val searchTerm = "pluto"
        val mockedCall: Call<RootModel> = mockGeneric()
        `when`(nasaImageApi.searchImages(eq(searchTerm))).thenReturn(mockedCall)
        store.dispatch(SetSearchText(searchTerm))
        val executeAction = ExecuteSearch()

        //Act
        store.dispatch(executeAction)

        //Assert
        collector.checkThat(store.state.listState.isLoading, IsEqual(true))
    }

    @Test
    fun executeSearchComplete_success() {
        //Arrange
        val searchTerm = "pluto"
        val (imageCollection, rootModel) = getTestData(1)
        val mockedCall: Call<RootModel> = mockGeneric()
        `when`(mockedCall.enqueue(ArgumentMatchers.any())).then {
            val callback: Callback<RootModel> = it.getArgument(0)
            val response = Response.success(rootModel)
            callback.onResponse(mockedCall, response)
        }
        `when`(nasaImageApi.searchImages(eq(searchTerm))).thenReturn(mockedCall)
        store.dispatch(SetSearchText(searchTerm))
        val executeAction = ExecuteSearch()

        //Act
        store.dispatch(executeAction)

        //Assert
        collector.checkThat(store.state.listState.isLoading, IsEqual(false))
        collector.checkThat(store.state.listState.imageCollection, IsEqual(imageCollection))
    }

    private fun getTestData(value: Int): Pair<ImageCollection, RootModel> {
        val imageLinks = listOf(ImageCollectionLink("https://example.com/$value"))
        val imageDataList = listOf(ImageCollectionItemData("Sample description $value", "Sample Title $value"))
        val image = ImageCollectionItem(imageDataList, "https://example.com/$value", imageLinks)
        val imageItemList = listOf(image)
        val imageCollection = ImageCollection("", imageItemList)
        val rootModel = RootModel(imageCollection)
        return Pair(imageCollection, rootModel)
    }

    inline fun <reified T : Any> mockGeneric() = mock(T::class.java)

}
