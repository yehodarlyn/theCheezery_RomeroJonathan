package romero.jonathan.thecheezery_romerojonathan.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import romero.jonathan.thecheezery_romerojonathan.data.Category
import romero.jonathan.thecheezery_romerojonathan.data.ProductsRepository

class ProductsViewModel(savedStateHandle: SavedStateHandle): ViewModel(){
    private val category = Category.valueOf(checkNotNull(savedStateHandle.get<String>("category")))

    val title: String = category.title
    val products = ProductsRepository.getByCategory(category)
}