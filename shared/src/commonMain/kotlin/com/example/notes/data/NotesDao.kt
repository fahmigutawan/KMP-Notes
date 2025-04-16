import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert
    suspend fun insert(item: NoteEntity)

    @Query(
        """
            UPDATE NoteEntity
            SET
                title = :title,
                description = :description,
                updatedAt = :updatedAt
            WHERE id = :id
        """
    )
    suspend fun update(
        id:Int,
        title:String,
        description:String,
        updatedAt:Long
    )

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("DELETE FROM NoteEntity WHERE id = :id")
    suspend fun delete(id: Int)
}