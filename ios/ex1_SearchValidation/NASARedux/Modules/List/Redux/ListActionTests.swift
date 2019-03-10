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
}
