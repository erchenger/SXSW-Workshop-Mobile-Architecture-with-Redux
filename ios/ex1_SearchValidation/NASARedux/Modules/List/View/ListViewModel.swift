//
//  ListViewModel.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation

struct ListViewModel: Equatable {
    
    var searchText: String?
    var searchValidationText: String?
    var showsActivityIndicator: Bool
    var itemViewModels: [ListCellViewModel]
    
    func shouldReloadList(from previousViewModel: ListViewModel?) -> Bool {
        guard let previousViewModel = previousViewModel else { return true }
        return itemViewModels != previousViewModel.itemViewModels
    }
}

extension ListViewModel {
    
    init(_ state: ListState) {
        searchText = state.searchTerm
        searchValidationText = nil
        showsActivityIndicator = false
        itemViewModels = []
    }
}
