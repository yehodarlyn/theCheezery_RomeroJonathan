package romero.jonathan.thecheezery_romerojonathan.model.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.ComboEntity
import romero.jonathan.thecheezery_romerojonathan.model.data.database.relation.ComboWithProducts

@Dao
interface ComboDao {
    @Insert
    suspend fun insert(item: ComboEntity): Long

    @Update
    suspend fun update(item: ComboEntity)

    @Delete
    suspend fun delete(item: ComboEntity)

    @Transaction
    @Query("SELECT * FROM Combos ORDER BY idCombo")
    fun withProducts(): Flow<List<ComboWithProducts>>
}
