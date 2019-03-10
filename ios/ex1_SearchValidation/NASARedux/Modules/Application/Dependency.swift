//
//  Dependency.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import ReSwift

class Dependency {
    
    static var shared = Dependency()
    
    lazy var store: Store<AppState> = {
        return Store<AppState>(reducer: appReducer,
                               state: nil,
                               middleware: makeMiddleware())
    }()
    
    lazy var api: API = {
        return API()
    }()
}

func makeMiddleware() -> [Middleware<AppState>] {
    return []
}
