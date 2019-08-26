package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.redux.AppReducer
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import org.junit.Before
import org.rekotlin.Store

class SetSearchTextIntegrationTests {

    private lateinit var store : Store<AppState>

    @Before
    fun setUp() {
        store = Store(AppReducer(), AppState())
    }
}
