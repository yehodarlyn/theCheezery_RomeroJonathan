package romero.jonathan.thecheezery_romerojonathan.model.data.database.entity

import androidx.room.*

@Entity(tableName = "Products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idProduct") val id: Int = 0,
    @ColumnInfo(name = "nameProduct") val name: String,
    @ColumnInfo(name = "priceProduct") val price: Float,
    @ColumnInfo(name = "imageProduct") val image: String?,
    @ColumnInfo(name = "descriptionProduct") val description: String?,
    val category: String
)
