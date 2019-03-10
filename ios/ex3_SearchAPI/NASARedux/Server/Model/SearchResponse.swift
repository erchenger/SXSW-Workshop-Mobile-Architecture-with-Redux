//
//  SearchResponse.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import SwiftyJSON

struct SearchResponse: Equatable {
    var results: [ImageSearchResult]
}

extension SearchResponse {
    
    init?(json: JSON) {
        guard let array = json["collection"]["items"].array else { return nil }
        self.results = array.compactMap { ImageSearchResult(json: $0) }
    }
}

extension SearchResponse: CustomStringConvertible {
    
    var description: String {
        return "\(results.count) results"
    }
}
