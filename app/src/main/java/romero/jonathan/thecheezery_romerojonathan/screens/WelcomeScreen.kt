package romero.jonathan.thecheezery_romerojonathan.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import romero.jonathan.thecheezery_romerojonathan.R
import romero.jonathan.thecheezery_romerojonathan.uitheme.Pinky
import romero.jonathan.thecheezery_romerojonathan.uitheme.Purple_grey

@Composable
fun WelcomeScreen(onGetStarted: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth().weight(0.6f), contentAlignment = Alignment.Center){
            Image(painter = painterResource(id = R.drawable.the_cheezery), contentDescription = "logo", modifier = Modifier.fillMaxSize())
        }
        Column(modifier = Modifier.fillMaxWidth().weight(0.4f).background(Purple_grey).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to The Cheezery", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))

            Text(text = "Home of the most wonderful desserts ever seen (and tasted) by the human being.", color = Color.White, fontSize = 14.sp, fontStyle = FontStyle.Italic)
            Spacer(Modifier.height(24.dp))

            Button(onClick = onGetStarted, colors = ButtonDefaults.buttonColors(containerColor = Pinky), shape = RoundedCornerShape(50)) {
                Text("Get started!", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(onGetStarted = {})
}