package ernesto.example.artflowv1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MCreadorFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_m_creador, container, false);

        // Obtener referencia a la ImageView en el diseño del fragmento
        ImageView btnGaleria = root.findViewById(R.id.btnGaleria);

        // Establecer un clic en la ImageView
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llamar a este método cuando se haga clic en la ImageView para abrir la galería
                abrirGaleria(view);
            }
        });

        return root;
    }

    // Método que se llama cuando se hace clic en la ImageView btnGaleria
    public void abrirGaleria(View view) {
        // Crear una intención para seleccionar contenido de la galería
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        // Iniciar la actividad de selección de imágenes desde el fragmento
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verificar si la solicitud de la galería fue exitosa
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Aquí puedes manejar la imagen seleccionada
            // La URI de la imagen seleccionada se encuentra en data.getData()
            // Por ejemplo, puedes obtener la URI de la siguiente manera:
            // Uri selectedImageUri = data.getData();
        }
    }
}
