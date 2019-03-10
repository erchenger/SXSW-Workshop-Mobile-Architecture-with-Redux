//
//  SearchResponse+TestExtensions.swift
//  NASAReduxTests
//
//  Created by John McIntosh on 3/2/19.
//  Copyright © 2019 Demo. All rights reserved.
//

@testable import NASARedux
import Foundation

extension SearchResponse {
    
    static var mockResponse: SearchResponse {
        return SearchResponse(json: MockServer.mockSuccessResponse.value!)!
    }
}
