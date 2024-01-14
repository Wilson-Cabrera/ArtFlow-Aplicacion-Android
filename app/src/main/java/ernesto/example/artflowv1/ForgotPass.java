package ernesto.example.artflowv1;

import static androidx.constraintlayout.motion.widget.TransitionBuilder.validate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPass extends AppCompatActivity {


    private Button btnRecup;
    private EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        btnRecup = findViewById(R.id.btnRecuperar);
        etEmail = findViewById(R.id.etEmailRecup);

        btnRecup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }


    public void validate(){
        String email = etEmail.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Correo invalido");
            return;
        }

        senEmail(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ForgotPass.this,iniciar_sesion.class);
        startActivity(intent);
        finish();
    }

    public void senEmail(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddres = email;

        auth.sendPasswordResetEmail(emailAddres)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPass.this, "Correo enviado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPass.this,iniciar_sesion.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(ForgotPass.this, "Correo invalido", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}