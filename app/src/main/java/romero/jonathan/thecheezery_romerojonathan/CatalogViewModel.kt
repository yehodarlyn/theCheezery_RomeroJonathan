package romero.jonathan.thecheezery_romerojonathan.viewmodel

import android.app.Application
import android.database.sqlite.SQLiteException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import romero.jonathan.thecheezery_romerojonathan.data.Category
import romero.jonathan.thecheezery_romerojonathan.model.data.database.AppDatabase
import romero.jonathan.thecheezery_romerojonathan.model.data.repository.CheezeryRepository

class CatalogViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = CheezeryRepository(AppDatabase.getInstance(app))
    val products = repository.products
    val combos = repository.combos
    var saving by mutableStateOf(false)
        private set
    var saved by mutableStateOf(false)
        private set
    var error by mutableStateOf<String?>(null)
        private set

    fun save(name: String, priceText: String, category: Category, productIds: Set<Int>?) {
        if (saving || saved) return
        val price = priceText.trim().replace(',', '.').toFloatOrNull()
        error = when {
            name.isBlank() -> "Ingresa un nombre"
            price == null || !price.isFinite() || price <= 0 -> "Ingresa un precio mayor a cero"
            productIds != null && productIds.isEmpty() -> "Selecciona al menos un producto"
            else -> null
        }
        if (error != null || price == null) return
        saving = true
        viewModelScope.launch {
            try {
                if (productIds == null) repository.addProduct(name, price, category)
                else repository.addCombo(name, price, productIds)
                saved = true
            } catch (e: SQLiteException) {
                error = "No se guardo.Intenta otra vez"
            } finally {
                saving = false
            }
        }
    }
}
