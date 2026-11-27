package romero.jonathan.thecheezery_romerojonathan.viewmodel


import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
)

class LoginViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) = _uiState.update { it.copy(email = value, emailError = null) }
    fun onPasswordChange(value: String) = _uiState.update { it.copy(password = value, passwordError = null) }

    fun onLoginClick(onSuccess: () -> Unit){
        val s = _uiState.value
        val emailError = when{
            s.email.isBlank() -> "El correo no puede estar vacío"
            !Patterns.EMAIL_ADDRESS.matcher(s.email).matches() -> "Formato de correo inválido"
            else -> null
        }
        val passwordError = if (s.password.isBlank()) "La contraseña no puede estar vacía" else null
        _uiState.update { it.copy(emailError = emailError, passwordError = passwordError) }
        if (emailError == null && passwordError == null) onSuccess()
    }
}