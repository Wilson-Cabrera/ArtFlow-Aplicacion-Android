package ernesto.example.artflowv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ernesto.example.artflowv1.R;
import ernesto.example.artflowv1.iniciar_sesion;

public class TendenciasFragment extends Fragment {

    private FirebaseAuth mAuth; // Instancia de FirebaseAuth para gestionar la autenticación

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_tendencias, container, false);

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Verificar el estado de autenticación al iniciar el fragmento
        FirebaseUser currentUser = mAuth.getCurrentUser();
        AccessToken currentFacebookAccessToken = AccessToken.getCurrentAccessToken();

        // Si no hay un usuario autenticado ni un AccessToken de Facebook,
        // redirigir a la actividad de inicio de sesión
        if (currentUser == null && currentFacebookAccessToken == null) {
            redirectToLoginActivity();
        }

        return view; // Devolver la vista inflada y configurada
    }

    // Método para redirigir a la actividad de inicio de sesión
    private void redirectToLoginActivity() {
        Intent intent = new Intent(requireContext(), iniciar_sesion.class);
        // Limpiar la pila de actividades y empezar la actividad de inicio de sesión
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish(); // Finalizar la actividad actual
    }
}
