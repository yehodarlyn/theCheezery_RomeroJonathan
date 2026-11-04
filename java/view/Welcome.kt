package view


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.thecheezery.R
import com.example.thecheezery.ui.theme.helloBunny


@Composable
fun Welcome(modifier: Modifier = Modifier){
    Column(
        modifier = modifier

    ){
        Image(
            painter = painterResource(id = R.drawable.the_cheezery),
            contentDescription = "Gráfica de gasto contra estimado",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Welcome to the cheezery",
                fontFamily = helloBunny,
                fontSize = 24.sp

            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomePreview(){
    Welcome()
}