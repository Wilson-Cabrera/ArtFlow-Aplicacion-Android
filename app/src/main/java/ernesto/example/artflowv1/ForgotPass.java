package ernesto.example.artflowv1;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPass extends AppCompatActivity {

    // Declaración de variables
    private Button btnRecup;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass); // Establecer el diseño de la actividad

        // Inicialización de vistas
        btnRecup = findViewById(R.id.btnRecuperar);
        etEmail = findViewById(R.id.etEmailRecup);

        // Configuración del OnClickListener para el botón de recuperación
        btnRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(); // Llamar al método validate() cuando se hace clic en el botón
            }
        });
    }

    // Método para validar el correo electrónico ingresado
    public void validate() {
        String email = etEmail.getText().toString().trim(); // Obtener el correo electrónico ingresado y eliminar espacios en blanco al principio y al final

        // Verificar si el campo de correo electrónico está vacío o no coincide con el patrón de correo electrónico
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Correo inválido"); // Establecer un mensaje de error en el campo de texto
            return; // Salir del método si la validación falla
        }

        senEmail(email); // Llamar al método senEmail() para enviar el correo electrónico de restablecimiento
    }

    // Método para enviar el correo electrónico de restablecimiento de contraseña
    public void senEmail(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance(); // Obtener una instancia de FirebaseAuth

        // Enviar un correo electrónico de restablecimiento de contraseña al correo electrónico especificado
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Verificar si la tarea se completó exitosamente
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPass.this, "Correo enviado", Toast.LENGTH_SHORT).show(); // Mostrar un mensaje de éxito
                            Intent intent = new Intent(ForgotPass.this, iniciar_sesion.class); // Crear una nueva intención para iniciar sesión
                            startActivity(intent); // Iniciar la actividad de inicio de sesión
                            finish(); // Finalizar la actividad actual
                        } else {
                            Toast.makeText(ForgotPass.this, "Correo inválido", Toast.LENGTH_SHORT).show(); // Mostrar un mensaje de error
                        }
                    }
                });
    }

    // Método para manejar el botón de retroceso
    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Llamar al método de la clase base para manejar el botón de retroceso

        // Crear una nueva intención para ir a la actividad de inicio de sesión
        Intent intent = new Intent(ForgotPass.this, iniciar_sesion.class);
        startActivity(intent); // Iniciar la actividad de inicio de sesión
        finish(); // Finalizar la actividad actual
    }
}