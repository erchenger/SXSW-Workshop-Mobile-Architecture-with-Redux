//
//  ListViewControllerTests.swift
//  NASAReduxTests
//
//  Created by John McIntosh on 3/2/19.
//  Copyright © 2019 Demo. All rights reserved.
//

@testable import NASARedux
import ReSwift
import XCTest

class ListViewControllerTests: XCTestCase {
    
    var defaultStore: Store<AppState>!
    var mockStore: MockStore!
    var viewController: ListViewController!
    
    override func setUp() {
        super.setUp()
        defaultStore = Dependency.shared.store
        
        mockStore = makeMockStore()
        Dependency.shared.store = mockStore
        
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        viewController = storyboard.instantiateViewController(withIdentifier: "ListViewController") as? ListViewController
        viewController.loadViewIfNeeded()
    }
    
    override func tearDown() {
        Dependency.shared.store = defaultStore
        super.tearDown()
    }
    
    func testTypingDispatchesAction() {
        viewController.textField.sendActions(for: .editingChanged)        
        XCTAssertTrue(mockStore.lastAction is ListAction.SetSearchText)
    }
}
