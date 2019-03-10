//
//  MockServer.swift
//  NASAReduxTests
//
//  Created by John McIntosh on 3/1/19.
//  Copyright © 2019 Demo. All rights reserved.
//

@testable import NASARedux
import Foundation
import Result
import SwiftyJSON

class MockServer: Server {
    
    var nextResponse: Result<JSON, AppError>?
    var mockCompletionClosure: ((@escaping (Result<JSON, AppError>) -> Void) -> Void)?

    func get(url: URL, completion: @escaping (Result<JSON, AppError>) -> Void) {
        if let mockCompletion = mockCompletionClosure {
            mockCompletion(completion)
        } else if let response = nextResponse {
            DispatchQueue.main.async {
                completion(response)
            }
        }
    }
    
    static var mockSuccessResponse: Result<JSON, AppError> {
        let response: [String: Any] = [
            "collection": [
                "items": [
                    [
                        "data": [
                            [
                                "media_type": "image",
                                "nasa_id": "1234",
                                "title": "Sample Title",
                                "description": "Sample description",
                                "href": "https://example.com",
                                "links": [
                                    [
                                        "href": "https://example.com"
                                    ]
                                ]
                            ]
                        ]
                    ]
                ]
            ]
        ]
        return .success(JSON(JSON(response)))
    }
    
    static var mockFailureResponse: Result<JSON, AppError> {
        return .failure(AppError.custom("mock error"))
    }
}
