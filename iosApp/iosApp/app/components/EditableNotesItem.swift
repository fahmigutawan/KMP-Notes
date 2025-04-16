//
//  EditableNotesItem.swift
//  iosApp
//
//  Created by PT Awan Data Indonesia on 11/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct EditableNotesItem: View {
    var onSaveClick: () -> Void
    var onCancelClick: () -> Void
    var title: String
    var onTitleChange: (String) -> Void
    var description: String
    var onDescriptionChange: (String) -> Void
    
    var body: some View {
        VStack(spacing: 8) {
            // Title TextField
            TextField("Title", text: Binding(get: { title }, set: onTitleChange))
                .textFieldStyle(.roundedBorder)
                .font(.headline)
            
            // Description TextField
            TextEditor(text: Binding(get: { description }, set: onDescriptionChange))
                .frame(minHeight: 100)
                .overlay(
                    RoundedRectangle(cornerRadius: 4)
                        .stroke(Color.gray.opacity(0.5), lineWidth: 1)
                )
                .overlay(
                    Group {
                        if description.isEmpty {
                            Text("Description")
                                .foregroundColor(Color(.placeholderText))
                                .frame(maxWidth: .infinity, alignment: .topLeading)
                                .padding(.top, 8)
                                .padding(.leading, 5)
                        }
                    },
                    alignment: .topLeading
                )
            
            // Action buttons
            HStack {
                Button(action: onCancelClick) {
                    Text("Cancel")
                        .foregroundColor(.secondary)
                }
                
                Spacer()
                
                Button(action: onSaveClick) {
                    Text("Save")
                        .bold()
                }
                .buttonStyle(.borderedProminent)
            }
            .padding(.top, 8)
        }
        .padding()
        .background(Color(.systemBackground))
        .cornerRadius(10)
        .shadow(color: Color.black.opacity(0.2), radius: 4, x: 0, y: 2)
    }
}
