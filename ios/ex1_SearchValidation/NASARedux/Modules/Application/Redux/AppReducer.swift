//
//  AppReducer.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import ReSwift

func appReducer(action: Action, state: AppState?) -> AppState {
    var state = state ?? AppState.makeDefault()
    
    if let action = action as? ListReducible {
        action.reduce(state: &state.listState)
    }
    
    return state
}

private extension AppState {
    
    static func makeDefault() -> AppState {
        return AppState(listState: ListState())
    }
}
