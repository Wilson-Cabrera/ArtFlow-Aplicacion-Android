
package ernesto.example.artflowv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

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

        // Redirigir a la actividad de inicio de sesión u otra actividad que desees
        Intent intent = new Intent(this, iniciar_sesion.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }
}
