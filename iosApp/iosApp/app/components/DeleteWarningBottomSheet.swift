//
//  DeleteWarningDialog.swift
//  iosApp
//
//  Created by PT Awan Data Indonesia on 11/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct DeleteWarningBottomSheet:View {
    var onCancel: () -> Void
    var onDeleteClick: () -> Void
    
    var body: some View {
        VStack(spacing: 20) {
            Text("Hapus Note")
                .font(.headline)
                .bold()
            
            Text("Apakah kamu yakin akan menghapus note ini?")
                .font(.body)
                .multilineTextAlignment(.center)
            
            HStack(spacing: 20) {
                // Cancel Button
                Button(action: onCancel) {
                    Text("Cancel")
                        .frame(minWidth: 80)
                }
                .buttonStyle(.bordered)
                
                // Delete Button
                Button(role: .destructive, action: onDeleteClick) {
                    Text("Delete")
                        .frame(minWidth: 80)
                }
                .buttonStyle(.bordered)
            }
        }
        //        .padding()
        //        .frame(minWidth: 280)
        //        .background(Color(.systemBackground))
        //        .cornerRadius(10)
        //        .shadow(radius: 5)
    }
}

struct DeleteSheetViewModifier: ViewModifier {
    @Binding var isPresented: Bool
    let onCancel: () -> Void
    let onDeleteClick: () -> Void
    
    func body(content: Content) -> some View {
        content
            .sheet(isPresented: $isPresented) {
                DeleteWarningBottomSheet(
                    onCancel: {
                        onCancel()
                        isPresented = false
                    },
                    onDeleteClick: {
                        onDeleteClick()
                        isPresented = false
                    }
                )
                .presentationDetents([.medium]) // Atur tinggi
            }
    }
}
