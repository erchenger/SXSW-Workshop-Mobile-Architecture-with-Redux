package com.elliott.sxswreduxworkshopandroid

import com.elliott.sxswreduxworkshopandroid.redux.ListState
import com.elliott.sxswreduxworkshopandroid.ui.search.MainUiModel
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    @Test
    fun canValidateSearch() {
        val expectations = mapOf(
                "" to "",
                "e" to "Enter at least two characters",
                "earth" to "Home Sweet Home",
                "mars" to "The Red Planet",
                "pluto" to "Formerly the 9th planet"
        )

        for (validation in expectations) {
            val listState = ListState(searchTerm = validation.key)
            collector.checkThat(MainUiModel.getValidationFromState(listState), IsEqual(validation.value))
        }
    }
}
