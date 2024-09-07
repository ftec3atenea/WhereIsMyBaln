package com.example.whereismybaln;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Recovery extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recovery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instanciamos el objeto FirebaseAuth para poder usar Firebase
        FirebaseAuth auth1 = FirebaseAuth.getInstance();

        // Aquí vamos a enlazar el mundo visual con el mundo lógico
        EditText input_user4 = findViewById(R.id.input_user4);
        Button btn_recovery4 = findViewById(R.id.btn_recovery4);
        TextView ytc4 = findViewById(R.id.ytc4);
        TextView ntc4 = findViewById(R.id.ntc4);

        // Ponemos a escuchar al botón de recovery
        btn_recovery4.setOnClickListener(v -> {

            // casteamos los datos a String
            String email = input_user4.getText().toString().trim();

            // llamamos al método sendPasswordResetEmail
            auth1.sendPasswordResetEmail(email);

            // vamos a generar un intent para ir a la pantalla de Home
            Intent irALogIn = new Intent(Recovery.this, LogIn.class);
            // activamos el intent
            startActivity(irALogIn);
            // vamos a generar un toast para notificar al usuario
            Toast.makeText(Recovery.this, "¡Revisa tu correo!", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });

        // Ponemos a escuchar al TextView de ya tienes cuenta
        ytc4.setOnClickListener(v -> {
            // vamos a generar un intent para ir a la pantalla de Home
            Intent irALogIn = new Intent(Recovery.this, LogIn.class);
            // activamos el intent
            startActivity(irALogIn);
            // vamos a generar un toast para notificar al usuario
            Toast.makeText(Recovery.this, "Es por aquí", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });

        // Ponemos a escuchar al TextView de no tienes cuenta
        ntc4.setOnClickListener(v -> {
            // vamos a generar un intent para ir a la pantalla de Home
            Intent irASignUp = new Intent(Recovery.this, SignUp.class);
            // activamos el intent
            startActivity(irASignUp);
            // vamos a generar un toast para notificar al usuario
            Toast.makeText(Recovery.this, "¡Registrate!", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
    }
}