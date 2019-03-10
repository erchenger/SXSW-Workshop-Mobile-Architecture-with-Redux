//
//  API.swift
//  NASARedux
//
//  Created by John McIntosh on 2/16/19.
//  Copyright © 2019 Demo. All rights reserved.
//

import Foundation
import Result

class API {
    
    private let server: Server
    
    init(server: Server = NetworkServer()) {
        self.server = server
    }
    
    func search(query: String, completion: @escaping (Result<SearchResponse, AppError>) -> Void) {
        guard let url = Endpoint.nasaSearch(query: query).url else {
            completion(.failure(AppError.custom("Unable to generate URL for search query: \(query)")))
            return
        }
        
        server.get(url: url) { result in
            switch result {
            case .success(let json):
                guard let response = SearchResponse(json: json) else {
                    completion(.failure(AppError.custom("Unable to parse search response.")))
                    return
                }
                completion(.success(response))
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getHighResolutionUrl(for searchResult: ImageSearchResult, completion: @escaping (Result<URL, AppError>) -> Void) {
        let url = searchResult.href
        server.get(url: url) { result in
            switch result {
            case .success(let json):
                guard let url = json.arrayValue.first?.url else {
                    completion(.failure(AppError.custom("Unable to parse url for high resolution image.")))
                    return
                }
                completion(.success(url))
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func getMetadata(for searchResult: ImageSearchResult, completion: @escaping (Result<ImageMetadata, AppError>) -> Void) {
        guard let url = Endpoint.nasaImageMetadata(for: searchResult.id).url else {
            completion(.failure(AppError.custom("Unable to generate metadata URL for \(searchResult.id).")))
            return
        }

        server.get(url: url) { result in
            switch result {
            case .success(let json):
                let metadata = ImageMetadata(json: json)
                completion(.success(metadata))
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
}
