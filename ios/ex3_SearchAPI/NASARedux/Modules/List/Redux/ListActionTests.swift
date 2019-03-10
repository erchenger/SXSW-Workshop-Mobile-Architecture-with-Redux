//
//  ListActionTests.swift
//  NASAReduxTests
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

@testable import NASARedux
import XCTest

class ListActionTests: XCTestCase {

    var store: MockStore!
    
    override func setUp() {
        super.setUp()
        store = makeMockStore()
    }

    func testSetSearchText() {
        store.dispatch(ListAction.SetSearchText(text: "pluto"))
        XCTAssertEqual(store.state.listState.searchTerm, "pluto")
    }
}
