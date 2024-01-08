package ernesto.example.artflowv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registrarEmail, registrarContrasena;
    private Button botonRegistrar;
    private TextView loginRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        // Inicializa la instancia de FirebaseAuth en onCreate()
        auth = FirebaseAuth.getInstance();

        // Inicializar elementos de la interfaz de usuario
        registrarEmail = findViewById(R.id.registrar_email);
        registrarContrasena = findViewById(R.id.registrar_contrasena);
        botonRegistrar = findViewById(R.id.boton_registrar);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        // Configurar click listener para el botón de registrar

        botonRegistrar.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View view) {
            // Obtener entrada del usuario
        String usuario = registrarEmail.getText().toString().trim();
        String contrasena = registrarContrasena.getText().toString().trim();

            // Validar entrada del usuario
            if (usuario.isEmpty()) {
                registrarEmail.setError("El correo electrónico no puede estar vacio");
            }
            if (contrasena.isEmpty()) {
                registrarContrasena.setError("La contraseña no puede estar vacia");
            } else {
             //Crea un nuevo usuario con correo electrónico y contraseña
            auth.createUserWithEmailAndPassword(usuario, contrasena)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Registro exitoso, muestra un mensaje de éxito
                                Toast.makeText(Registrarse.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                                // Redirigir a la actividad de inicio de sesión
                                startActivity(new Intent(Registrarse.this, iniciar_sesion.class));
                            } else {
                                // Error al registrarse, muestra un mensaje de error
                                Toast.makeText(Registrarse.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        }
        });

        // Establecer detector de clics para el texto de redireccionamiento de inicio de sesión
        loginRedirectText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // Redirigir a la actividad de inicio de sesión
                startActivity(new Intent(Registrarse.this, iniciar_sesion.class));
            }
        });




    }
}