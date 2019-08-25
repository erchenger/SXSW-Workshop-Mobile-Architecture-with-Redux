package com.elliott.sxswreduxworkshopandroid.redux.middleware

import com.elliott.sxswreduxworkshopandroid.network.NasaImageApi
import com.elliott.sxswreduxworkshopandroid.redux.AppState
import org.rekotlin.Action
import org.rekotlin.DispatchFunction
import org.rekotlin.Middleware

class AsyncMiddleware(private val imageApi: NasaImageApi) {

    internal val middleware: Middleware<AppState> = { dispatch, state ->
        { next ->
            { action ->
                val nextAction = next(action)

                when (action) {
                    is AsyncActionable -> {
                        action.postReduce(dispatch, state.invoke(), imageApi)
                    }
                }
                nextAction
            }

        }

    }
}

interface AsyncActionable : Action {
    fun postReduce(dispatchFunction: DispatchFunction, state: AppState?, imageApi: NasaImageApi)
}