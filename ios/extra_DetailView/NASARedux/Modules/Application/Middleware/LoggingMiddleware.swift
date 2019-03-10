//
//  LoggingMiddleware.swift
//  NASARedux
//
//  Created by John McIntosh on 2/26/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import ReSwift

let loggingMiddleware: Middleware<AppState> = { dispatch, getState in
    return { next in
        return { action in
            print("▶︎ Dispatching: \(action)")
            next(action)
        }
    }
}
