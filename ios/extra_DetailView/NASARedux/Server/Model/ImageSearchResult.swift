//
//  ImageSearchResult.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import SwiftyJSON

struct ImageSearchResult: Equatable {
    var id: String
    var previewUrl: URL
    var title: String
    var description: String
    var href: URL
}


extension ImageSearchResult {
    
    init?(json: JSON) {
        let data = json["data"][0]
        guard data["media_type"].string == "image" else { return nil }
        
        guard let id = data["nasa_id"].string,
            let previewUrl = json["links"][0]["href"].url,
            let title = data["title"].string,
            let description = data["description"].string,
            let href = json["href"].url
            else {
                return nil
        }
        
        self.id = id
        self.previewUrl = previewUrl
        self.title = title
        self.description = description
        self.href = href
    }
}
