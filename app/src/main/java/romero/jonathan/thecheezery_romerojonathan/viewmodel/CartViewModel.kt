package romero.jonathan.thecheezery_romerojonathan.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import romero.jonathan.thecheezery_romerojonathan.model.data.database.AppDatabase
import romero.jonathan.thecheezery_romerojonathan.model.data.repository.CheezeryRepository

class CartViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = CheezeryRepository(AppDatabase.getInstance(app))
    val items = repository.cart.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(name: String, price: Float) {
        viewModelScope.launch { repository.addToCart(name, price) }
    }

    fun remove(id: Int) {
        viewModelScope.launch { repository.removeFromCart(id) }
    }
}
