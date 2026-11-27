package romero.jonathan.thecheezery_romerojonathan.model.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.ProductEntity

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(item: ProductEntity): Long

    @Update
    suspend fun update(item: ProductEntity)

    @Delete
    suspend fun delete(item: ProductEntity)

    @Query("SELECT * FROM Products ORDER BY idProduct")
    fun all(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM Products WHERE category = :category ORDER BY idProduct")
    fun byCategory(category: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM Products WHERE idProduct = :id")
    suspend fun byId(id: Int): ProductEntity?

    @Query("SELECT * FROM Products WHERE nameProduct LIKE '%' || :name || '%' ORDER BY idProduct")
    fun search(name: String): Flow<List<ProductEntity>>
}
