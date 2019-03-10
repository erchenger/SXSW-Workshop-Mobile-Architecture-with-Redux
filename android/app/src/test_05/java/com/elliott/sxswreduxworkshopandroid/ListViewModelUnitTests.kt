package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.redux.*
import com.elliott.sxswreduxworkshopandroid.ui.search.ListViewModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ErrorCollector
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import redux.api.Action
import redux.api.Store

class ListViewModelUnitTests {

    @get:Rule
    val collector = ErrorCollector()

    private lateinit var listViewModel: ListViewModel
    private lateinit var mockApplication: App
    private lateinit var store: Store<AppState>

    @Before
    fun setUp() {
        store = mockGeneric()
        mockApplication = mock(App::class.java)
        `when`(mockApplication.store).thenReturn(store)
        listViewModel = ListViewModel(mockApplication)
    }

    @Test
    fun doesDispatchSearchAction() {
        //Arrange
        val searchTerm = "pluto"
        val argumentCaptor = ArgumentCaptor.forClass(Action::class.java)

        //Act
        listViewModel.onSearch(searchTerm)

        //Assert
        verify(store, times(2)).dispatch(argumentCaptor.capture())
        assertTrue(argumentCaptor.allValues[0] is SetSearchText)
        assertTrue(argumentCaptor.allValues[1] is ExecuteSearch)
    }

    @Test
    fun doesDispatchExecuteSearch() {
        //Act
        listViewModel.submitPressed()

        //Assert
        verify(store).dispatch(ArgumentMatchers.isA(ExecuteSearch::class.java))
    }

    inline fun <reified T : Any> mockGeneric() = mock(T::class.java)
}
