//
//  AsyncMiddleware.swift
//  NASARedux
//
//  Created by John McIntosh on 3/1/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import ReSwift

protocol AsyncActionable: Action {
    func postReduce(dispatch: @escaping DispatchFunction, state: AppState, getState: @escaping () -> AppState?)
}

let asyncMiddleware: Middleware<AppState> = { dispatch, getState in
    return { next in
        return { action in
            next(action)
            
            if let action = action as? AsyncActionable, let state = getState() {
                action.postReduce(dispatch: dispatch, state: state, getState: getState)
            }
        }
    }
}
