//
//  Server.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import Result
import SwiftyJSON

protocol Server {
    func get(url: URL, completion: @escaping (Result<JSON, AppError>) -> Void)
}

class NetworkServer: Server {
    
    private let urlSession: URLSession
    
    init() {
        self.urlSession = URLSession(configuration: .default)
    }
    
    func get(url: URL, completion: @escaping (Result<JSON, AppError>) -> Void) {
        let task = urlSession.dataTask(with: url) { (data, response, error) in
            guard error == nil else {
                DispatchQueue.main.async {
                    completion(.failure(AppError.error(error!)))
                }
                return
            }
            
            guard let data = data else {
                DispatchQueue.main.async {
                    completion(.failure(AppError.custom("API response data was nil even though there was no error.")))
                }
                return
            }
            
            do {
                let json = try JSON(data: data)
                DispatchQueue.main.async {
                    completion(.success(json))
                }
            }
            catch {
                DispatchQueue.main.async {
                    completion(.failure(AppError.error(error)))
                }
            }
        }
        task.resume()
    }
}
