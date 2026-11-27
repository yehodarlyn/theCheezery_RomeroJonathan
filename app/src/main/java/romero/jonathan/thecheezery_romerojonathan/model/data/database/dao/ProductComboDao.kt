package romero.jonathan.thecheezery_romerojonathan.model.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.ProductComboEntity

@Dao
interface ProductComboDao {
    @Insert
    suspend fun insert(items: List<ProductComboEntity>)
}
