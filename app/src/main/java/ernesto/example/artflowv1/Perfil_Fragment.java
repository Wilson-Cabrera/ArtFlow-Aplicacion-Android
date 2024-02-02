package ernesto.example.artflowv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil_, container, false);

        // Acceder a los elementos de la vista, como el botón
        Button btnMenuHamburguesa = view.findViewById(R.id.btnMenuHamburguesa);

        // Manejar el click del botón
        btnMenuHamburguesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });

        return view;
    }

    // Mostrar el menú emergente
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        popupMenu.inflate(R.menu.menu_items_hamburguesa); // Llamando al menú en res/menu

        // Manejar los clicks en los elementos del menú
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item1) {
                    // Lógica para el primer Item (Cerrar sesión)
                    cerrarSesion();
                    return true;
                } else if (item.getItemId() == R.id.item2) {
                    // Lógica para el segundo Item
                    showToast("Item 2 seleccionado");
                    return true;
                } else if (item.getItemId() == R.id.item3) {
                    // Lógica para el tercer Item
                    showToast("Item 3 seleccionado");
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Mostrar menú
        popupMenu.show();
    }

    // Método para cerrar sesión
    private void cerrarSesion() {
        // Cerrar sesión en Firebase Authentication
        FirebaseAuth.getInstance().signOut();

        // Cerrar sesión en Google
        GoogleSignIn.getClient(requireContext(), GoogleSignInOptions.DEFAULT_SIGN_IN).signOut();

        // Cerrar sesión en Facebook
        LoginManager.getInstance().logOut();

        // Redirigir a la actividad de inicio de sesión
        redirectToLoginActivity();
    }

    // Método para redirigir a la actividad de inicio de sesión
    private void redirectToLoginActivity() {
        Intent intent = new Intent(requireContext(), iniciar_sesion.class);
        // Limpiar la pila de actividades y empezar la actividad de inicio de sesión
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        requireActivity().finish(); // Finalizar la actividad actual
        // Llamada para recrear el menú después de cerrar sesión
        requireActivity().invalidateOptionsMenu();
    }

    // Método para mostrar mensajes de error
    private void mostrarMensajeError(String mensaje) {
        showToast("Se produjo un error. " + mensaje + " Inténtalo de nuevo más tarde.");
    }

    // Método para mostrar mensajes Toast
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
