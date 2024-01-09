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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 1;
    private EditText registrarEmail, registrarContrasena;
    private Button botonRegistrar, GoogleAuth;
    private TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        // Inicializa la instancia de FirebaseAuth en onCreate()
        mAuth = FirebaseAuth.getInstance();

        // Inicializar elementos de la interfaz de usuario
        registrarEmail = findViewById(R.id.registrar_email);
        registrarContrasena = findViewById(R.id.registrar_contrasena);
        botonRegistrar = findViewById(R.id.boton_registrar);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        GoogleAuth = findViewById(R.id.button2);

        // Configurar click listener para el botón de registrar
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    // Crea un nuevo usuario con correo electrónico y contraseña
                    mAuth.createUserWithEmailAndPassword(usuario, contrasena)
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
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirigir a la actividad de inicio de sesión
                startActivity(new Intent(Registrarse.this, iniciar_sesion.class));
            }
        });

        // Configuración del OnClickListener para el botón de Google Sign-In
        GoogleAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });

        // Configuración de opciones para Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Creación del cliente de Google Sign-In
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void googleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuth(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso, muestra un mensaje de éxito
                            Toast.makeText(Registrarse.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                            // Redirigir a la actividad de inicio de sesión
                            startActivity(new Intent(Registrarse.this, MainActivity.class));
                        } else {
                            // Error al registrarse, muestra un mensaje de error
                            Toast.makeText(Registrarse.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado de la actividad de inicio de sesión de Google
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Éxito en la autenticación de Google, autentica en Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account);
            } catch (ApiException e) {
                // Manejo de errores en caso de fallo en la autenticación de Google
                Toast.makeText(this, "Error en la autenticación de Google", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
