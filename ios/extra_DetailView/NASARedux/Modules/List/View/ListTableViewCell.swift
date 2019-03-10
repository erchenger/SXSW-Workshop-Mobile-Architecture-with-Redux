//
//  ListTableViewCell.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Kingfisher
import UIKit

class ListTableViewCell: UITableViewCell {

    @IBOutlet weak var listImage: UIImageView!
    @IBOutlet weak var listTitle: UILabel!
    @IBOutlet weak var listDetail: UILabel!
    
    func update(with viewModel: ListCellViewModel) {
        listImage.kf.setImage(with: viewModel.image, options: [.transition(.fade(0.3))])
        listTitle.text = viewModel.title
        listDetail.text = viewModel.detail
    }
}
