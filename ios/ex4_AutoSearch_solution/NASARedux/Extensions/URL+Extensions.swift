//
//  URL+Extensions.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation

extension URL {
    
    init(staticString: StaticString) {
        self.init(string: "\(staticString)")!
    }
}
