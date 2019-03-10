//
//  Endpoint.swift
//  NASARedux
//
//  Created by John McIntosh on 2/26/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation

struct Endpoint {
    
    let host: String
    let path: String
    let queryItems: [URLQueryItem]
}


extension Endpoint {

    var url: URL? {
        var components = URLComponents()
        components.scheme = "https"
        components.host = host
        components.path = path
        components.queryItems = queryItems
        return components.url
    }

    static func nasaSearch(query: String) -> Endpoint {
        return Endpoint(
            host: "images-api.nasa.gov",
            path: "/search",
            queryItems: [
                URLQueryItem(name: "q", value: query)
            ]
        )
    }
    
    static func nasaImageMetadata(for itemId: String) -> Endpoint {
        return Endpoint(
            host: "images-assets.nasa.gov",
            path: "/image/\(itemId)/metadata.json",
            queryItems: [])
    }
}
