package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.redux.AppReducer
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import org.junit.Before
import redux.Redux
import redux.api.Store

class SetSearchTextIntegrationTests {

    private lateinit var store : Store<AppState>

    @Before
    fun setUp() {
        store = Redux.createStore(AppReducer(), AppState())
    }
}
