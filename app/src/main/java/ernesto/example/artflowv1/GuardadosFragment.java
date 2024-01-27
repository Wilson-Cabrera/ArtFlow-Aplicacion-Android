package ernesto.example.artflowv1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class GuardadosFragment extends Fragment {
    private FirebaseAuth mAuth; // Instancia de FirebaseAuth para gestionar la autenticación
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guardados, container, false);
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

        // Configurar el botón de cerrar sesión
        Button btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion(); // Llamada al método cerrarSesion al hacer clic en el botón
            }
        });

        return view; // Devolver la vista inflada y configurada
    }

    // Método para cerrar sesión en Firebase, Google y Facebook
    private void cerrarSesion() {
        // Cerrar sesión en Firebase Authentication
        mAuth.signOut();

        // Cerrar sesión en Google
        GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();

        // Cerrar sesión en Facebook
        LoginManager.getInstance().logOut();

        // Verificar el estado de autenticación después de cerrar sesión
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Si no hay usuario autenticado, la sesión se cerró correctamente
        if (currentUser == null) {
            showToast("¡Sesión cerrada exitosamente!");
            requireActivity().finish(); // Finalizar la actividad actual
        } else {
            // Si hay un usuario, hubo un error al cerrar sesión
            mostrarMensajeError("Error al cerrar sesión. Por favor, inténtalo de nuevo.");
        }
    }

    // Método para mostrar mensajes de error
    private void mostrarMensajeError(String mensaje) {
        showToast("Se produjo un error. " + mensaje + " Inténtalo de nuevo más tarde.");
    }

    // Método para mostrar mensajes Toast
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
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
