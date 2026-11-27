package romero.jonathan.thecheezery_romerojonathan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import romero.jonathan.thecheezery_romerojonathan.data.Product

class CartViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<Product>>(emptyList())
    val items: StateFlow<List<Product>> = _items.asStateFlow()

    val total: StateFlow<Float> = _items
        .map { list -> list.sumOf { it.price.toDouble() }.toFloat() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0f)

    fun addProduct(product: Product) = _items.update { it + product }

    fun removeAt(index: Int) = _items.update { list ->
        list.toMutableList().also { it.removeAt(index) }
    }
}