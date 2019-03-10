//
//  UIActivityIndicatorView+Extensions.swift
//  NASARedux
//
//  Created by John McIntosh on 3/3/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import UIKit

extension UIActivityIndicatorView {
    
    func animating(when shouldBeAnimating: Bool) {
        if shouldBeAnimating && !self.isAnimating {
            startAnimating()
        } else if !shouldBeAnimating && self.isAnimating {
            stopAnimating()
        }
    }
}
