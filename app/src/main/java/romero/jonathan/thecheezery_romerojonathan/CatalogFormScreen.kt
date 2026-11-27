package romero.jonathan.thecheezery_romerojonathan.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import romero.jonathan.thecheezery_romerojonathan.data.Category
import romero.jonathan.thecheezery_romerojonathan.viewmodel.CatalogViewModel

@Composable
fun CatalogFormScreen(isCombo: Boolean, onSaved: () -> Unit, model: CatalogViewModel = viewModel()) {
    var name by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf(Category.HOT_DRINKS) }
    var selected by rememberSaveable { mutableStateOf(intArrayOf()) }
    val products by model.products.collectAsState(initial = emptyList())
    LaunchedEffect(model.saved) { if (model.saved) onSaved() }

    LazyColumn(Modifier.fillMaxSize().padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            Text(if (isCombo) "Create combo" else "Add new product", style = MaterialTheme.typography.headlineMedium)
            OutlinedTextField(name, { name = it }, label = { Text("Name") }, singleLine = true)
            OutlinedTextField(price, { price = it }, label = { Text("Price") }, singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
            Text(if (isCombo) "Choose products" else "Choose a category", Modifier.padding(top = 12.dp))
        }
        if (isCombo) {
            items(products, key = { it.id }) { product ->
                val checked = product.id in selected
                Row(Modifier.fillMaxWidth().toggleable(value = checked, role = Role.Checkbox) { enabled ->
                    selected = if (enabled) selected + product.id else selected.filter { it != product.id }.toIntArray()
                }.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked, onCheckedChange = null)
                    Text(product.name, Modifier.padding(start = 12.dp))
                }
            }
        } else {
            items(Category.entries.toList()) { option ->
                FilterChip(selected = category == option, onClick = { category = option },
                    label = { Text(option.title) })
            }
        }
        item {
            model.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            Button(enabled = !model.saving && !model.saved, onClick = {
                model.save(name, price, category, if (isCombo) selected.toSet() else null)
            }) { Text("Save") }
        }
    }
}
