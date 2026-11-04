package ui


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thecheezery.R


//fuente de la aplicacion
val helloBunny = FontFamily(
    Font(R.font.hello_bunny, FontWeight.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(fontFamily = helloBunny, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontFamily = helloBunny, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontFamily = helloBunny, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp)
)



