package romero.jonathan.thecheezery_romerojonathan.model.data.database.entity

import androidx.room.*

@Entity(tableName = "Combos")
data class ComboEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idCombo") val id: Int = 0,
    @ColumnInfo(name = "nameCombo") val name: String,
    @ColumnInfo(name = "priceCombo") val price: Float
)
