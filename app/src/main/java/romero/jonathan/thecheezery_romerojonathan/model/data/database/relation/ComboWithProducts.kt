package romero.jonathan.thecheezery_romerojonathan.model.data.database.relation

import androidx.room.*
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.*

data class ComboWithProducts(
    @Embedded val combo: ComboEntity,
    @Relation(parentColumn = "idCombo", entityColumn = "idProduct",
        associateBy = Junction(ProductComboEntity::class))
    val products: List<ProductEntity>
)
