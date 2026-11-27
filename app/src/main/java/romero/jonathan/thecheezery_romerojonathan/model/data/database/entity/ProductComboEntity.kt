package romero.jonathan.thecheezery_romerojonathan.model.data.database.entity

import androidx.room.*

@Entity(
    tableName = "ProductsCombo",
    foreignKeys = [
        ForeignKey(entity = ProductEntity::class, parentColumns = ["idProduct"],
            childColumns = ["idProduct"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = ComboEntity::class, parentColumns = ["idCombo"],
            childColumns = ["idCombo"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("idCombo"), Index(value = ["idProduct", "idCombo"], unique = true)]
)
data class ProductComboEntity(
    @PrimaryKey(autoGenerate = true) val idProductCombo: Int = 0,
    val idProduct: Int,
    val idCombo: Int
)
