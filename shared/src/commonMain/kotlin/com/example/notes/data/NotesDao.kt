import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(item:NoteEntity)

    @Update
    suspend fun replace(item:NoteEntity)

    @Query("SELECT * FROM NoteEntity")
    suspend fun getAll():List<NoteEntity>

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    suspend fun delete(id:Int)
}