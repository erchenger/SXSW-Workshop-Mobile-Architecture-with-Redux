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
}
