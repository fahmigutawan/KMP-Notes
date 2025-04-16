//
//  EmptyContent.swift
//  iosApp
//
//  Created by PT Awan Data Indonesia on 11/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct EmptyContent: View {
    var modifier: some ViewModifier = EmptyModifier()
    var onAddClick: () -> Void
    
    var body: some View {
        VStack(
            alignment: .center,
            spacing: 16
        ) {
            Image(systemName: "trash")
                .font(.system(size: 64))
                .accessibilityHidden(true)
            
            Text("Notes Kosong")
            
            Button(action: onAddClick) {
                Text("Tambah")
            }
        }
        .modifier(modifier)
    }
}
