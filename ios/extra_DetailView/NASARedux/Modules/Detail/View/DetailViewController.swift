//
//  DetailViewController.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import ReSwift
import UIKit

class DetailViewController: UIViewController, StoreSubscriber {
    
    @IBOutlet weak var imageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var detailLabel: UILabel!

    private let store = Dependency.shared.store
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        store.subscribe(self) { appstate in
            return appstate.select({ state -> ImageSearchResult? in
                return state.listState.selectedItem
            }).skipRepeats()
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        store.unsubscribe(self)
    }
    
    func newState(state: ImageSearchResult?) {
        guard let state = state else { return }
        self.update(with: DetailViewModel(state))
    }

    func update(with viewModel: DetailViewModel) {
        imageView.kf.setImage(with: viewModel.image, options: [.transition(.fade(0.3))])
        titleLabel.text = viewModel.title
        detailLabel.text = viewModel.detail
    }
}
