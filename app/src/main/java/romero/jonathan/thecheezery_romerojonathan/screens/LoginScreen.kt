package romero.jonathan.thecheezery_romerojonathan.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import romero.jonathan.thecheezery_romerojonathan.R
import romero.jonathan.thecheezery_romerojonathan.uitheme.Brighter_Pink
import romero.jonathan.thecheezery_romerojonathan.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
){
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(R.drawable.grupo1), contentDescription = "The cheezery")
        Spacer(Modifier.height(48.dp))

        TextField(
            value = state.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Correo electrónico") },
            isError = state.emailError != null,
            supportingText = {state.emailError?.let { Text(it) }},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            label = {Text("Contraseña")},
            isError = state.passwordError != null,
            supportingText = {state.passwordError?.let { Text(it) }},
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {viewModel.onLoginClick(onLoginSuccess)},
            colors = ButtonDefaults.buttonColors(containerColor = Brighter_Pink)
        ) { Text("Entrar", color = Color.White)}
    }
}