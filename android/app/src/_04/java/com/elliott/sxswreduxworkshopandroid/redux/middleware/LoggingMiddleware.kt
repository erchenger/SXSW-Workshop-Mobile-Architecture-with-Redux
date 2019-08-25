package com.elliott.sxswreduxworkshopandroid.redux.middleware

import android.util.Log
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import org.rekotlin.Middleware

internal val LoggingMiddleware: Middleware<AppState> = { dispatch, state ->
    { next ->
        { action ->
            Log.d("APP", "Dispatching $action")
            Log.d("APP", "SearchTeam before reducer ${state.invoke()?.listState?.searchTerm}")
            val nextAction = next(action)
            Log.d("APP", "SearchTeam after reducer ${state.invoke()?.listState?.searchTerm}")
            nextAction
        }
    }
}