//
//  ListAction.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import ReSwift

enum ListAction { /* Empty enum for namespacing */ }

protocol ListReducible: Action {
    func reduce(state: inout ListState)
}

extension ListAction {
    
    /// This sample action shows the structure that a typical action might follow.
    struct SampleAction: ListReducible {
        func reduce(state: inout ListState) {
            // Modify the passed-in state.
        }
    }
}
