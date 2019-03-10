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
    
    func testMisorderedSearchResponses() {
        // Configure a test expectation to wait until both responses have been received.
        let expect = expectation(description: "Wait for network calls to complete")

        var api1Complete = false
        var api2Complete = false
        let apiCompletion = {
            guard api1Complete && api2Complete else { return }
            expect.fulfill()
            
            // When the test expectations are fulfilled, assert that the value in the
            // state is "successful". The second call to the API will return a successful
            // response, but first call to the API, will return a failed response after
            // the second call has already completed.
            XCTAssertTrue(self.store.state.listState.searchResponse!.isSuccess)
        }
        
        // Configure api1 to return a failed response after an 0.1 second delay.
        let api1: API = {
            let server = MockServer()
            server.mockCompletionClosure = { completion in
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
                    api1Complete = true
                    completion(MockServer.mockFailureResponse)
                    apiCompletion()
                }
            }
            return API(server: server)
        }()

        // Configure api1 to return a successful response immediately.
        let api2: API = {
            let server = MockServer()
            server.mockCompletionClosure = { completion in
                api2Complete = true
                completion(MockServer.mockSuccessResponse)
                apiCompletion()
            }
            return API(server: server)
        }()
        
        // Simulate typing to trigger multiple parallel API calls.
        store.dispatch(ListAction.SetSearchText(text: "m"))
        store.dispatch(ListAction.ExecuteSearch(api: api1))
        store.dispatch(ListAction.SetSearchText(text: "ma"))
        store.dispatch(ListAction.ExecuteSearch(api: api2))

        // Allow the test to complete.
        waitForExpectations(timeout: 1.0)
    }
}
