import SwiftUI
import shared

struct MainView: View {
    @State var title = "Sample Title"
    @State var description = "Sample description"
    @StateObject private var viewModel = MainViewModel()
    
    var body: some View {
        VStack{
            if(viewModel.notes.isEmpty && !viewModel.showAddNote){
                EmptyContent(
                    onAddClick: {
                        viewModel.showAddNote = true
                    }
                )
            } else {
                ScrollView{
                    VStack(alignment: .center, spacing: 12) {
                        Spacer()
                        
                        ForEach(viewModel.notes, id: \.id){ note in
                            if(viewModel.selectedNoteIdToEdit != note.id){
                                NotesItem(
                                    title: note.title,
                                    description: note.description_,
                                    createdAt: note.createdAt,
                                    onEditClick: {
                                        viewModel.selectedNoteIdToEdit = note.id
                                        viewModel.title = note.title
                                        viewModel.description = note.description_
                                    },
                                    onDeleteClick: {
                                        viewModel.selectedNoteIdToDelete = note.id
                                    }
                                )
                                .padding(.horizontal, 16)
                            } else {
                                EditableNotesItem(
                                    onSaveClick: {
                                        viewModel.createOrUpdate()
                                        viewModel.selectedNoteIdToEdit = nil
                                        viewModel.resetInput()
                                    },
                                    onCancelClick: {
                                        viewModel.selectedNoteIdToEdit = nil
                                    },
                                    title: viewModel.title,
                                    onTitleChange: {s in viewModel.title = s},
                                    description: viewModel.description,
                                    onDescriptionChange: {s in viewModel.description = s}
                                )
                                .padding(.horizontal, 16)
                            }
                        }
                        
                        if(viewModel.showAddNote){
                            EditableNotesItem(
                                onSaveClick: {
                                    viewModel.createOrUpdate()
                                    viewModel.showAddNote = false
                                    viewModel.resetInput()
                                },
                                onCancelClick: {
                                    viewModel.showAddNote = false
                                },
                                title: viewModel.title,
                                onTitleChange: {s in viewModel.title = s},
                                description: viewModel.description,
                                onDescriptionChange: {s in viewModel.description = s}
                            )
                            .padding(.horizontal, 16)
                        } else {
                            Button("Tambah Note Baru"){
                                viewModel.showAddNote = true
                            }
                            .frame(width: .infinity)
                            .padding(.horizontal, 16)
                        }
                        
                        Spacer()
                    }
                }
            }
        }.sheet(
            isPresented: Binding(
                get: { viewModel.selectedNoteIdToDelete != nil },
                set: { value in
                    if !value {
                        viewModel.selectedNoteIdToDelete = nil
                    }
                }
            ),
            content: {
                DeleteWarningBottomSheet(
                    onCancel: {
                        viewModel.selectedNoteIdToDelete = nil
                    },
                    onDeleteClick: {
                        viewModel.deleteNote()
                        viewModel.selectedNoteIdToDelete = nil
                    }
                ).presentationDetents([.medium])
            }
        )
    }
}
