//
//  ListViewController.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import UIKit
import ReSwift

class ListViewController: UIViewController, StoreSubscriber {

    private let store = Dependency.shared.store

    // MARK: IBOutlets
    
    @IBOutlet weak var textField: UITextField! {
        didSet {
            textField.addTarget(self, action: #selector(editingChanged(textField:)), for: .editingChanged)
        }
    }
    
    @IBOutlet weak var validationLabel: UILabel! {
        didSet {
            validationLabel.text = nil
        }
    }
    
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    
    @IBOutlet weak var tableView: UITableView!

    // MARK: User Actions
    
    @IBAction func submitPressed() {
        triggerSearch()
    }
    
    @objc private func editingChanged(textField: UITextField) {
        store.dispatch(ListAction.SetSearchText(text: textField.text))
        store.dispatch(ListAction.ExecuteSearch())
    }
    
    private func triggerSearch() {
        textField.resignFirstResponder()
        store.dispatch(ListAction.ExecuteSearch())
    }

    // MARK: Redux subscription
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        store.subscribe(self) { appstate in
            return appstate.select({ state -> ListState in
                return state.listState
            }).skipRepeats()
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        store.unsubscribe(self)
    }

    func newState(state: ListState) {
        self.update(viewModel: ListViewModel(state))
    }

    private var viewModel: ListViewModel?

    func update(viewModel: ListViewModel) {
        let previousViewModel = self.viewModel
        self.viewModel = viewModel
        
        textField.text = viewModel.searchText
        validationLabel.text = viewModel.searchValidationText
        
        // I often create helpers like this `animating(when:)` function for wrapping the
        // logic around conditionally updating particular UI components. These types of
        // helpers allow the code for UI changes to remain concise and readable.
        activityIndicator.animating(when: viewModel.showsActivityIndicator)

        // We don't want to reload the table each time the search term changes so this convenience
        // function allows us to only reload when the contents of the table have changed.
        if viewModel.shouldReloadList(from: previousViewModel) {
            tableView.reloadData()
        }
    }
}

extension ListViewController: UITextFieldDelegate {
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        triggerSearch()
        return false
    }
}

extension ListViewController: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel?.itemViewModels.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let itemViewModel = viewModel?.itemViewModels[indexPath.row] else { fatalError() }
        let cell = tableView.dequeueReusableCell(withIdentifier: "BasicCell", for: indexPath) as! ListTableViewCell
        cell.update(with: itemViewModel)
        return cell
    }
}
