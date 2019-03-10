//
//  MockStore.swift
//  NASAReduxTests
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

@testable import NASARedux
import ReSwift
import XCTest

/// Store implementation for tests that exposes tracking of the `lastAction` that was dispatched.
class MockStore: Store<AppState> {
    
    /// Dispatch an empty action to configure the default state of AppState.
    fileprivate func initializeDefaultState() {
        dispatch(MockAction())
        dispatchedActions = []
    }
    
    var dispatchedActions: [Action]?
    
    var lastAction: Action? {
        return dispatchedActions?.last
    }
    
    override func _defaultDispatch(action: Action) {
        dispatchedActions?.append(action)
        super._defaultDispatch(action: action)
        pendingExpectations.forEach { expectation in
            if let condition = expectationConditions[expectation] {
                if condition(action) {
                    expectation.fulfill()
                }
            }
        }
    }
    
    typealias ExpectationCondition = (Action) -> Bool
    var pendingExpectations: [XCTestExpectation] = []
    var expectationConditions: [XCTestExpectation: ExpectationCondition] = [:]
    func fulfillExpectation(_ expectation: XCTestExpectation, when condition: @escaping ExpectationCondition) {
        pendingExpectations.append(expectation)
        expectationConditions[expectation] = condition
    }
}

func makeMockStore() -> MockStore {
    let store = MockStore(reducer: appReducer,
                          state: nil,
                          middleware: makeMiddleware())
    store.initializeDefaultState()
    return store
}

private struct MockAction: Action { }
