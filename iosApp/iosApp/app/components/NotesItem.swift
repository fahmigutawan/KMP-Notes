//
//  NotesItem.swift
//  iosApp
//
//  Created by PT Awan Data Indonesia on 11/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct NotesItem:View{
    let title: String
    let description: String
    let createdAt: Int64
    let onEditClick: () -> Void
    let onDeleteClick: () -> Void
    
    @State private var isExpanded = false
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text(title)
                    .font(.headline)
                
                Spacer()
                
                Button(action: {
                    withAnimation {
                        isExpanded.toggle()
                    }
                }) {
                    Image(systemName: isExpanded ? "chevron.up" : "chevron.down")
                }
            }
            
            Text(description)
                .lineLimit(isExpanded ? nil : 1)
                .animation(.default, value: isExpanded)
                .padding(.vertical, 4)
            
            HStack {
                Text("\(createdAt)")
                    .font(.caption)
                
                Spacer()
                
                HStack {
                    Button(action: onDeleteClick) {
                        Image(systemName: "trash")
                            .foregroundColor(.red)
                    }
                    
                    Button(action: onEditClick) {
                        Image(systemName: "pencil")
                    }
                }
            }
            .font(.body)
        }
        .padding()
        .background(Color(.systemBackground))
        .cornerRadius(10)
        .shadow(color: Color.black.opacity(0.2), radius: 4, x: 0, y: 2)
    }
}
