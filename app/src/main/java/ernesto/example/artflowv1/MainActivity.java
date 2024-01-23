package ernesto.example.artflowv1;
// Importación de paquetes necesarios


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// Declaración de la clase MainActivity que hereda de AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Declaración de variables miembro
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    // Método llamado cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Establece el diseño de la actividad

        // Inicialización de variables de interfaz de usuario
        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.FrameLayout);

        // Configuración del escuchador para la Bottom Navigation Bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Según el elemento seleccionado, carga el fragmento correspondiente
                if (itemId == R.id.NavHome) {
                    loadFragment(new HomeFragment(), false);
                } else if (itemId == R.id.NavSearch) {
                    loadFragment(new TendenciasFragment(), false);
                } else if (itemId == R.id.NavNotification) {
                    loadFragment(new TendenciasFragment(), false);
                } else if (itemId == R.id.NavProfile) {
                    loadFragment(new TendenciasFragment(), false);
                }

                return true; // Devuelve true para indicar que el evento ha sido manejado
            }
        });

        // Carga inicial de un fragmento al iniciar la actividad
        loadFragment(new HomeFragment(), true);
    }

    // Método para cargar un fragmento en el FrameLayout
    private void loadFragment(Fragment fragment, boolean isAppInitialize) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Si es la inicialización de la aplicación, se agrega el fragmento; de lo contrario, se reemplaza
        if (isAppInitialize) {
            fragmentTransaction.add(R.id.FrameLayout, fragment);
        } else {
            fragmentTransaction.replace(R.id.FrameLayout, fragment);
        }

        fragmentTransaction.commit();
    }
}
