package ernesto.example.artflowv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class iniciar_sesion extends AppCompatActivity {
    private FirebaseAuth Auth;
    private EditText loginemail, loginpassword;
    private Button loginbutton;
    private TextView registrarseRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        // Inicializar FirebaseAuth
        Auth = FirebaseAuth.getInstance();
        // Inicializar UI elements
        loginemail = findViewById(R.id.etEmail);
        loginpassword = findViewById(R.id.etContrasena);
        loginbutton = findViewById(R.id.btIngresar);
        registrarseRedirectText = findViewById(R.id.tvRegistrarse);

        // Configurar click listener para el botón de inicio de sesión
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        // Obtener correo electrónico y contraseña del usuario
        String email = loginemail.getText().toString();
        String pass = loginpassword.getText().toString();

                // Validar el correo electrónico y la contraseña
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        // Iniciar sesión con el correo electrónico y la contraseña
                        Auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        // Mostrar mensaje de inicio de sesión exitoso
                                        Toast.makeText(iniciar_sesion.this, "Inicio de sesion exitosa", Toast.LENGTH_SHORT).show();

                                        // Redirigir a la actividad principal y finalizar la actividad actual
                                        startActivity(new Intent(iniciar_sesion.this, MainActivity.class));
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                    // Mostrar mensaje de inicio de sesión fallido
                                        Toast.makeText(iniciar_sesion.this, "Error de inicio de sesion", Toast.LENGTH_SHORT).show();

                                    }
                                });

                    } else {
                        loginpassword.setError("La contraseña no puede estar vacia");
                    }
                } else if (!email.isEmpty()) {
                    loginemail.setError("El correo electronico no puede estar vacio");
                } else {
                    loginemail.setError("Por favor ingrese un correo electronico valido");
                }
            }
        });
            // Configurar click listener para el texto de redirección al registro
            registrarseRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Redirigir a la actividad de registro
                startActivity(new Intent(iniciar_sesion.this, Registrarse.class));
            }
        });
    }
}