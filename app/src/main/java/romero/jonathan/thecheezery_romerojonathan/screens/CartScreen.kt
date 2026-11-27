package romero.jonathan.thecheezery_romerojonathan.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import romero.jonathan.thecheezery_romerojonathan.R
import romero.jonathan.thecheezery_romerojonathan.uitheme.HelloBunny
import romero.jonathan.thecheezery_romerojonathan.uitheme.Less_Purple
import romero.jonathan.thecheezery_romerojonathan.uitheme.Pinky
import romero.jonathan.thecheezery_romerojonathan.uitheme.Very_purple
import romero.jonathan.thecheezery_romerojonathan.viewmodel.CartViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel){
    val items by cartViewModel.items.collectAsState()
    val total by cartViewModel.total.collectAsState()

    Column(Modifier.fillMaxSize().padding(horizontal = 32.dp)) {
        Text(
            "My Order",
            fontFamily = HelloBunny,
            fontSize = 88.sp,
            color = Pinky,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        if (items.isEmpty()){
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center){
                Text("Add some products to your order!", fontSize = 20.sp, textAlign = TextAlign.Center)
            }
        }else{
            LazyColumn(Modifier.weight(1f)) {
                itemsIndexed(items){ index, product ->
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(product.name, fontSize = 20.sp, color = Color.DarkGray, modifier = Modifier.weight(1f))
                        Text("\$${product.price}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Very_purple)
                        IconButton(onClick = {cartViewModel.removeAt(index)}) {
                            Icon(painterResource(R.drawable.delete), contentDescription = "Eliminar", tint = Pinky)
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Text("Total", fontSize = 40.sp)
            Spacer(Modifier.width(8.dp))
            Text("\$$total", fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Less_Purple)
        }
    }
}