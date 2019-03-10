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
            // We already have the entered search term in the app state, but we don't want
            // to actually execute a network call if the search term is null.
            guard let searchTerm = state.listState.searchTerm else { return }
            api.search(query: searchTerm) { result in
                dispatch(ExecuteSearchComplete(result: result))
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
}
