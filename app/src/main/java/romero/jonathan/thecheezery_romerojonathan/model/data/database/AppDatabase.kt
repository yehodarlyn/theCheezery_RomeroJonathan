package romero.jonathan.thecheezery_romerojonathan.model.data.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import romero.jonathan.thecheezery_romerojonathan.data.Category
import romero.jonathan.thecheezery_romerojonathan.data.ProductsRepository
import romero.jonathan.thecheezery_romerojonathan.model.data.database.dao.*
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.*

@Database(entities = [ProductEntity::class, ComboEntity::class, ProductComboEntity::class,
    CartItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun comboDao(): ComboDao
    abstract fun productComboDao(): ProductComboDao
    abstract fun cartDao(): CartDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance ?: synchronized(this) {
            val app = context.applicationContext
            instance ?: Room.databaseBuilder(app, AppDatabase::class.java, "cheezery.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        for (category in Category.entries) {
                            for (product in ProductsRepository.getByCategory(category)) {
                                db.execSQL(
                                    """INSERT INTO Products
                                        (nameProduct, priceProduct, imageProduct, descriptionProduct, category)
                                        VALUES (?, ?, ?, ?, ?)""",
                                    arrayOf(product.name, product.price.toDouble(),
                                        app.resources.getResourceEntryName(product.image),
                                        product.description.orEmpty(), category.name)
                                )
                            }
                        }
                    }
                }).build().also { instance = it }
        }
    }
}
