//
//  ImageDetailResult.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation

struct ImageDetailResult: Equatable {
    
    var searchResult: ImageSearchResult
    var highResImage: URL?
    var metadata: String?
}
