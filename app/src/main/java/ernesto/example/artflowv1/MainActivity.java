
package ernesto.example.artflowv1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import ernesto.example.artflowv1.R;
import ernesto.example.artflowv1.iniciar_sesion;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
    }

    public void cerrarSesion(View view) {
        // Cerrar sesión en Firebase Authentication
        mAuth.signOut();

        // Cerrar sesión en Google
        GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();

        // Verificar el estado de autenticación después de cerrar sesión
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Redirigir a la actividad de inicio de sesión u otra actividad que desees
            Intent intent = new Intent(this, iniciar_sesion.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Cierra la actividad actual
        } else {
            // Manejar el caso si el cierre de sesión no tuvo éxito
            // Esto podría incluir la actualización de la interfaz de usuario o mostrar un mensaje de error
            mostrarMensajeError("Error al cerrar sesión. Por favor, inténtalo de nuevo.");
        }
    }

    private void mostrarMensajeError(String mensaje) {
        Toast.makeText(this, "Se produjo un error. " + mensaje + " Inténtalo de nuevo más tarde.", Toast.LENGTH_SHORT).show();
    }
}
