package romero.jonathan.thecheezery_romerojonathan.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import romero.jonathan.thecheezery_romerojonathan.R
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import romero.jonathan.thecheezery_romerojonathan.model.data.database.entity.ProductEntity
import romero.jonathan.thecheezery_romerojonathan.uitheme.DisabledGrey
import romero.jonathan.thecheezery_romerojonathan.uitheme.HelloBunny
import romero.jonathan.thecheezery_romerojonathan.uitheme.Less_Purple
import romero.jonathan.thecheezery_romerojonathan.uitheme.Pinky
import romero.jonathan.thecheezery_romerojonathan.uitheme.PurpleGrey40
import romero.jonathan.thecheezery_romerojonathan.uitheme.Very_purple
import romero.jonathan.thecheezery_romerojonathan.viewmodel.CartViewModel
import romero.jonathan.thecheezery_romerojonathan.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    cartViewModel: CartViewModel,
    viewModel: ProductsViewModel = viewModel()
) {
    val products by viewModel.products.collectAsState(initial = emptyList())
    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Text(
            text = viewModel.title,
            fontFamily = HelloBunny,
            fontSize = 88.sp,
            color = Pinky,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products, key = { it.id }) { product ->
                ProductItem(product = product, onAddClick = { cartViewModel.add(product.name, product.price) })
            }
        }
    }
}

@Composable
private fun ProductItem(product: ProductEntity, onAddClick: () -> Unit) {
    val context = LocalContext.current
    val imageId = product.image?.let { context.resources.getIdentifier(it, "drawable", context.packageName) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(imageId?.takeIf { it != 0 } ?: R.drawable.the_cheezery),
            contentDescription = product.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp).clip(CircleShape)
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                product.name,
                fontFamily = FontFamily.SansSerif,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Less_Purple
            )
            Text(
                product.description ?: "",
                fontSize = 13.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.width(8.dp))
        Text("\$${product.price}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Very_purple)
        Spacer(Modifier.width(8.dp))
        FilledIconButton(
            onClick = onAddClick,
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Very_purple,
                contentColor = Color.White,
                disabledContainerColor = DisabledGrey,
                disabledContentColor = PurpleGrey40
            )
        ) {
            Icon(painterResource(R.drawable.add_cart), contentDescription = "Agregar al carrito", tint = Color.White)
        }
    }
}