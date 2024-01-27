package ernesto.example.artflowv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    private ImageView btnIrATendencias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Buscar el botón por ID
        btnIrATendencias = view.findViewById(R.id.btnIrATendencias);

        // Configurar el listener de clic para el botón
        btnIrATendencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción a realizar cuando se hace clic en el botón (abrir un nuevo fragmento)
                cargarTendenciasFragment();
            }
        });

        // Verificar la autenticación del usuario y realizar otras configuraciones según sea necesario
        if (!usuarioEstaAutenticado()) {
            // Si el usuario no está autenticado, redirigir a la pantalla de inicio de sesión
            redirigirALogin();
        } else {
            // Configuración de la Toolbar solo si el usuario está autenticado
            configurarToolbar(view);
        }

        return view;
    }

    private boolean usuarioEstaAutenticado() {
        // Verificación con Firebase (correo y contraseña)
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            return true;
        }

        // Verificación con Facebook
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null && !accessToken.isExpired()) {
            return true;
        }

        // Verificación con Google
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(requireActivity());
        return googleSignInAccount != null;
    }

    private void redirigirALogin() {
        // Redirigir a la pantalla de inicio de sesión
        startActivity(new Intent(requireActivity(), iniciar_sesion.class));
        // Finalizar la actividad actual para que el usuario no pueda volver atrás con el botón de retroceso
        requireActivity().finish();
    }

    private void configurarToolbar(View view) {
        // Configuración de la Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        // Acceder a la actividad asociada al fragmento
        AppCompatActivity activity = (AppCompatActivity) requireActivity();

        // Verificar si la actividad es una AppCompatActivity y si tiene una ActionBar
        if (activity != null && activity.getSupportActionBar() != null) {
            // Configurar la Toolbar como ActionBar
            activity.setSupportActionBar(toolbar);

            // Puedes habilitar la flecha de retroceso en la ActionBar si es necesario
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
            }
        }
        // Puedes agregar más configuraciones de la Toolbar aquí si es necesario
    }

    private void cargarTendenciasFragment() {
        // Crea una instancia del fragmento TendenciasFragment
        TendenciasFragment tendenciasFragment = new TendenciasFragment();

        // Obtiene el administrador de fragmentos y comienza una transacción
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Reemplaza el contenido actual del contenedor por el nuevo fragmento
        fragmentTransaction.replace(R.id.FrameLayout, tendenciasFragment);

        // Agrega la transacción al historial de retroceso
        fragmentTransaction.addToBackStack(null);

        // Confirma la transacción
        fragmentTransaction.commit();
    }
}
