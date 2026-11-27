package romero.jonathan.thecheezery_romerojonathan.model.data.database.entity

import androidx.room.*

@Entity(tableName = "CartItems")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idCartItem") val id: Int = 0,
    val name: String,
    val price: Float
)
