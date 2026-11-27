package romero.jonathan.thecheezery_romerojonathan.model.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.CartItemEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM CartItems ORDER BY idCartItem")
    fun items(): Flow<List<CartItemEntity>>

    @Insert
    suspend fun add(item: CartItemEntity)

    @Query("DELETE FROM CartItems WHERE idCartItem = :id")
    suspend fun remove(id: Int)
}
