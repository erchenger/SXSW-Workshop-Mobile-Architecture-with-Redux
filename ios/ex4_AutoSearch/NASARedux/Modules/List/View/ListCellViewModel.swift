//
//  ListCellViewModel.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation

struct ListCellViewModel: Equatable {
    
    var searchItem: ImageSearchResult
    var image: URL
    var title: String
    var detail: String
}

extension ListCellViewModel {
    
    init(_ state: ImageSearchResult) {
        self.searchItem = state
        self.image = state.previewUrl
        self.title = state.title
        self.detail = state.description
    }
}
