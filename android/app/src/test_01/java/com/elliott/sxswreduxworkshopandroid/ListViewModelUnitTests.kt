package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.redux.*
import com.elliott.sxswreduxworkshopandroid.ui.search.ListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.rules.ErrorCollector
import org.mockito.Mockito.*
import org.rekotlin.Store

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

    inline fun <reified T : Any> mockGeneric() = mock(T::class.java)
}
