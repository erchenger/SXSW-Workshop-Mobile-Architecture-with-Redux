//
//  Result+Extensions.swift
//  NASARedux
//
//  Created by John McIntosh on 3/1/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Result

extension Result {
    
    var isSuccess: Bool {
        switch self {
        case .success: return true
        case .failure: return false
        }
    }
    
    var isFailure: Bool {
        return !isSuccess
    }
}
