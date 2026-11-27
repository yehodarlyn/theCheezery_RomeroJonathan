package romero.jonathan.thecheezery_romerojonathan.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import romero.jonathan.thecheezery_romerojonathan.viewmodel.CatalogViewModel

@Composable
fun CombosScreen(onCreate: () -> Unit, onAdd: (String, Float) -> Unit,
                 model: CatalogViewModel = viewModel()) {
    val combos by model.combos.collectAsState(initial = emptyList())
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Combos", style = MaterialTheme.typography.headlineLarge)
        Button(onClick = onCreate) { Text("Create combo") }
        if (combos.isEmpty()) Text("No combos yet.")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(combos, key = { it.combo.id }) { entry ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Text(entry.combo.name, style = MaterialTheme.typography.titleMedium)
                        Text(entry.products.joinToString { it.name })
                        TextButton(onClick = { onAdd(entry.combo.name, entry.combo.price) }) {
                            Text("Add to cart · $" + entry.combo.price)
                        }
                    }
                }
            }
        }
    }
}
