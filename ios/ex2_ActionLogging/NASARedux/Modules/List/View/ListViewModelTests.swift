//
//  ListViewModelTests.swift
//  NASAReduxTests
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

@testable import NASARedux
import XCTest

class ListViewModelTests: XCTestCase {
    
    func testSearchText() {
        let state = ListState(searchTerm: "earth")
        let viewModel = ListViewModel(state)
        XCTAssertEqual(viewModel.searchText, "earth")
    }
    
    func testSearchValidation() {
        let expectations: [(String?, String?)] = [
            (nil, nil),
            ("", nil),
            ("e", "Enter at least two characters"),
            ("earth", "Home Sweet Home"),
            ("mars", "The Red Planet"),
            ("pluto", "Formerly the 9th planet")
        ]
        
        for (searchTerm, expectedValidation) in expectations {
            let state = ListState(searchTerm: searchTerm)
            let viewModel = ListViewModel(state)
            XCTAssertEqual(viewModel.searchValidationText, expectedValidation)
        }
    }
}
