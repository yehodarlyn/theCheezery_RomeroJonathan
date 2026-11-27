package romero.jonathan.thecheezery_romerojonathan.data

import romero.jonathan.thecheezery_romerojonathan.coldDrinks
import romero.jonathan.thecheezery_romerojonathan.hotDrinks
import romero.jonathan.thecheezery_romerojonathan.salties
import romero.jonathan.thecheezery_romerojonathan.sweets

object ProductsRepository {
    fun getByCategory(category: Category): List<Product> = when (category) {
        Category.HOT_DRINKS -> hotDrinks
        Category.COLD_DRINKS -> coldDrinks
        Category.SALTIES -> salties
        Category.SWEETS -> sweets
    }

}