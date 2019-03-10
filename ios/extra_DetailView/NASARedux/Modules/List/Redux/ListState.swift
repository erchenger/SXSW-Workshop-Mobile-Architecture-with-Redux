//
//  ListState.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import Result

struct ListState: Equatable {
    
    var searchTerm: String?
    var isLoading: Bool
    var searchResponse: Result<SearchResponse, AppError>?
    var selectedItem: ImageSearchResult?

    init(searchTerm: String? = nil, isLoading: Bool = false, searchResponse: Result<SearchResponse, AppError>? = nil) {
        self.searchTerm = searchTerm
        self.isLoading = isLoading
        self.searchResponse = searchResponse
    }
}
