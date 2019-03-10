//
//  DetailViewModel.swift
//  NASARedux
//
//  Created by John McIntosh on 2/23/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation

struct DetailViewModel {
    
    var title: String
    var detail: String
    var image: URL?
}

extension DetailViewModel {
    
    init(_ item: ImageSearchResult) {
        title = item.title
        detail = item.description
        image = item.previewUrl
    }
}
