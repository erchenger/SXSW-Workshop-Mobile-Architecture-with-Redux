//
//  ImageMetadata.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import SwiftyJSON

struct ImageMetadata {
    
    var description: String
}

extension ImageMetadata {
    
    init(json: JSON) {
        self.description = json.debugDescription
    }
}
