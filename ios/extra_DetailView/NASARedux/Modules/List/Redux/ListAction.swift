//
//  ListAction.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import Result
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

    struct SetSearchText: ListReducible {
        let text: String?
        func reduce(state: inout ListState) {
            state.searchTerm = text
        }
    }
    
    struct ExecuteSearch: ListReducible, AsyncActionable {
        private let api: API
        init(api: API = Dependency.shared.api) {
            self.api = api
        }
        
        func reduce(state: inout ListState) {
            state.isLoading = true
        }
        
        func postReduce(dispatch: @escaping DispatchFunction, state: AppState, getState: @escaping () -> AppState?) {
            guard let requestSearchTerm = state.listState.searchTerm else { return }
            api.search(query: requestSearchTerm) { result in
                let currentSearchTerm = getState()?.listState.searchTerm
                if requestSearchTerm == currentSearchTerm {
                    dispatch(ExecuteSearchComplete(result: result))
                } else {
                    // This response is from a previously-entered search term, so ignore it.
                }
            }
        }
    }
    
    struct ExecuteSearchComplete: ListReducible {
        let result: Result<SearchResponse, AppError>
        func reduce(state: inout ListState) {
            state.isLoading = false
            state.searchResponse = result
        }
    }
    
    struct Selected: ListReducible {
        let item: ImageSearchResult
        func reduce(state: inout ListState) {
            state.selectedItem = item
        }
    }
}
