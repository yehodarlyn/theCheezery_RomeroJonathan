package romero.jonathan.thecheezery_romerojonathan.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import romero.jonathan.thecheezery_romerojonathan.R
import romero.jonathan.thecheezery_romerojonathan.data.Category
import romero.jonathan.thecheezery_romerojonathan.uitheme.Brighter_Pink
import romero.jonathan.thecheezery_romerojonathan.uitheme.Less_Purple
import romero.jonathan.thecheezery_romerojonathan.uitheme.Purple_grey

@Composable
fun MenuScreen(
    onCategoryClick: (Category) -> Unit,
    onCombos: () -> Unit,
    onAddProduct: () -> Unit,
    onCartClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            FilledIconButton(
                onClick = onCartClick,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Less_Purple,
                    contentColor = Color.White
                )
            ) {
                Icon(painterResource(R.drawable.cart), contentDescription = "Cart")
            }
        }
        Image(painterResource(R.drawable.grupo2), contentDescription = "The cheezery")
        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MenuColumn(
                items = listOf(
                    "Hot drinks" to { onCategoryClick(Category.HOT_DRINKS) },
                    "Salties" to { onCategoryClick(Category.SALTIES) },
                    "Combos" to onCombos
                ),
                modifier = Modifier.weight(1f)
            )
            MenuColumn(
                items = listOf(
                    "Cold drinks" to { onCategoryClick(Category.COLD_DRINKS) },
                    "Sweets" to { onCategoryClick(Category.SWEETS) },
                    "Add new product" to onAddProduct
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun MenuColumn(items: List<Pair<String, () -> Unit>>, modifier: Modifier = Modifier){
    Column(
        modifier = modifier.background(
            Brush.verticalGradient(listOf(Brighter_Pink, Less_Purple, Purple_grey))
        )
    ) {
        items.forEach { (label, onClick) ->
            TextButton(onClick = onClick, modifier = Modifier.fillMaxWidth().height(56.dp)) {
                Text(label, color = Color.White)
            }
        }
    }
}