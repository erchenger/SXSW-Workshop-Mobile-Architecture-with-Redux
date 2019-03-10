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
        searchValidationText = validationText(for: state.searchTerm)
        showsActivityIndicator = false
        itemViewModels = []
    }
}

private func validationText(for text: String?) -> String? {
    guard let text = text else { return nil }
    if text.caseInsensitiveCompare("earth") == .orderedSame { return "Home Sweet Home" }
    if text.caseInsensitiveCompare("pluto") == .orderedSame { return "Formerly the 9th planet" }
    if text.caseInsensitiveCompare("mars") == .orderedSame { return "The Red Planet" }
    if text.count > 0 && text.count < 2 {
        return "Enter at least two characters"
    }
    return nil
}
