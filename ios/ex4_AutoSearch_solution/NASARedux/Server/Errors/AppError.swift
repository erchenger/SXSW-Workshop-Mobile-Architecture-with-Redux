//
//  AppError.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation

enum AppError: Equatable, LocalizedError {
    
    case error(Error)
    case custom(String)
    
    var localizedDescription: String {
        switch self {
        case .error(let error):
            return error.localizedDescription
        case .custom(let string):
            return string
        }
    }

    static func == (lhs: AppError, rhs: AppError) -> Bool {
        switch (lhs, rhs) {
        case (.error(let lhsValue), .error(let rhsValue)): return (lhsValue as NSError) == (rhsValue as NSError)
        case (.custom(let lhsValue), .custom(let rhsValue)): return lhsValue  == rhsValue
        case (.error, .custom): return false
        case (.custom, .error): return false
        }
    }
}
