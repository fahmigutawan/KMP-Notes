//
//  iOSViewModel.swift
//  iosApp
//
//  Created by PT Awan Data Indonesia on 11/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared
import KMPNativeCoroutinesAsync

class MainViewModel: ObservableObject{
    lazy var builder = NotesDbBuilder().getDatabaseBuilder()
    lazy var source = NotesSource(
        db: NotesDbWrapper().getRoomDatabase(
            builder: builder
        )
    )
    @Published var notes: [NoteEntity] = []
    
    @Published var selectedNoteIdToEdit:Int32? = nil
    @Published var selectedNoteIdToDelete:Int32? = nil

    @Published var title:String = ""
    @Published var description:String = ""
    @Published var showAddNote:Bool = false
    
    func resetInput(){
        title = ""
        description = ""
    }
    
    func createOrUpdate(){
        Task{
            do{
                if(selectedNoteIdToEdit == nil){
                    try await asyncFunction(
                        for: source.insertNote(
                            title: title,
                            description: description,
                            createdAt: 0,
                            updatedAt: 0
                        )
                    )
                    print("Success insert")
                } else {
                    guard let id = selectedNoteIdToEdit else {
                        return
                    }
                    
                    try await asyncFunction(
                        for: source.updateNote(
                            id: id,
                            title: title,
                            description: description,
                            updatedAt: 0
                        )
                    )
                    
                    print("Success update")
                }
            }catch{
                print("Failed with error: \(error)")
            }
            
        }
    }
    
    func deleteNote(){
        Task{
            do{
                guard let id = selectedNoteIdToDelete else {
                    return
                }
                try await asyncFunction(for: source.deleteNote(id: id))
                print("Success delete")
            }catch{
                print("Failed with error: \(error)")
            }
        }
    }
    
    init(){
        Task{
            do {
                let sequence = asyncSequence(for: source.getAllNotes())
                for try await letters in sequence {
                    notes = letters
                }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
}
