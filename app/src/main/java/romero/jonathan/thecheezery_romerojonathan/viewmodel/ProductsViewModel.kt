package romero.jonathan.thecheezery_romerojonathan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import romero.jonathan.thecheezery_romerojonathan.data.Category
import romero.jonathan.thecheezery_romerojonathan.model.data.database.AppDatabase
import romero.jonathan.thecheezery_romerojonathan.model.data.repository.CheezeryRepository

class ProductsViewModel(app: Application, state: SavedStateHandle) : AndroidViewModel(app) {
    private val category = Category.valueOf(checkNotNull(state.get<String>("categoryia")))
    val title = category.title
    val products = CheezeryRepository(AppDatabase.getInstance(app)).productsByCategory(category)
}
