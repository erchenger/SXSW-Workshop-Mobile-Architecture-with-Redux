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
    
    func testExecuteSearch_success() {
        store.dispatch(ListAction.SetSearchText(text: "pluto"))

        let server = MockServer()
        server.nextResponse = MockServer.mockSuccessResponse
        let api = API(server: server)
        
        let expect = expectation(description: "Wait for network call to complete")
        store.fulfillExpectation(expect, when: { action -> Bool in
            guard let action = action as? ListAction.ExecuteSearchComplete else { return false }
            return action.result.isSuccess
        })
        
        let action = ListAction.ExecuteSearch(api: api)
        store.dispatch(action)
        
        XCTAssertTrue(store.state.listState.isLoading)
        
        waitForExpectations(timeout: 1.0)
    }

    func testExecuteSearch_failure() {
        store.dispatch(ListAction.SetSearchText(text: "pluto"))
        
        let server = MockServer()
        server.nextResponse = MockServer.mockFailureResponse
        let api = API(server: server)
        
        let expect = expectation(description: "Wait for network call to complete")
        store.fulfillExpectation(expect, when: { action -> Bool in
            guard let action = action as? ListAction.ExecuteSearchComplete else { return false }
            return action.result.isFailure
        })
        
        let action = ListAction.ExecuteSearch(api: api)
        store.dispatch(action)
        
        waitForExpectations(timeout: 1.0)
    }
    
    func testExecuteSearchComplete_success() {
        store.state.listState.isLoading = true
        
        let action = ListAction.ExecuteSearchComplete(result: .success(.mockResponse))
        store.dispatch(action)
        
        XCTAssertFalse(store.state.listState.isLoading)
        XCTAssertEqual(store.state.listState.searchResponse?.value, SearchResponse.mockResponse)
    }
}
