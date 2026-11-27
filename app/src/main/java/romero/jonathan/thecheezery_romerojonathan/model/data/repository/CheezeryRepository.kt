package romero.jonathan.thecheezery_romerojonathan.model.data.repository

import androidx.room.withTransaction
import romero.jonathan.thecheezery_romerojonathan.data.Category
import romero.jonathan.thecheezery_romerojonathan.model.data.database.AppDatabase
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.*

class CheezeryRepository(private val db: AppDatabase) {
    val products = db.productDao().all()
    val combos = db.comboDao().withProducts()
    val cart = db.cartDao().items()

    fun productsByCategory(category: Category) = db.productDao().byCategory(category.name)

    suspend fun addProduct(name: String, price: Float, category: Category) {
        require(name.isNotBlank() && price.isFinite() && price > 0)
        db.productDao().insert(ProductEntity(name = name.trim(), price = price,
            image = null, description = null, category = category.name))
    }

    suspend fun addCombo(name: String, price: Float, productIds: Set<Int>) {
        require(name.isNotBlank() && price.isFinite() && price > 0 && productIds.isNotEmpty())
        db.withTransaction {
            val id = db.comboDao().insert(ComboEntity(name = name.trim(), price = price)).toInt()
            db.productComboDao().insert(productIds.map { ProductComboEntity(idProduct = it, idCombo = id) })
        }
    }

    suspend fun addToCart(name: String, price: Float) =
        db.cartDao().add(CartItemEntity(name = name, price = price))

    suspend fun removeFromCart(id: Int) = db.cartDao().remove(id)
}
