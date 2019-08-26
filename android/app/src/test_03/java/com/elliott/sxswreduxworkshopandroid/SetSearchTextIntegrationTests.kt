package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.redux.AppReducer
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import com.elliott.sxswreduxworkshopandroid.redux.SetSearchText
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.rekotlin.Store

class SetSearchTextIntegrationTests {

    private lateinit var store : Store<AppState>

    @Before
    fun setUp() {
        store = Store(AppReducer(), AppState())
    }

    @Test
    fun canSearchText() {
        val action = SetSearchText("pluto")
        store.dispatch(action)

        assertEquals("pluto", store.state.listState.searchTerm)
    }
}
