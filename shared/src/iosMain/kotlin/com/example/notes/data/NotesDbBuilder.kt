import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.data.NotesDb
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<NotesDb> {
    val dbFilePath = documentDirectory() + "/my_room.db"
    return Room.databaseBuilder<NotesDb>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDirectory,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}