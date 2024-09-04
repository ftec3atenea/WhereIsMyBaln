package com.example.whereismybaln;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Aquí vamos a enlazar el mundo visual con el mundo lógico
        Button btn_login2 = findViewById(R.id.btn_login2);
        TextView forgot2 = findViewById(R.id.forgot2);
        TextView ntc2 = findViewById(R.id.ntc2);

        //Ponemos a escuchar al botón de login
        btn_login2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vamos a generar un intent para ir a la pantalla de Home
                Intent irASearch = new Intent(LogIn.this, Search.class);
                // activamos el intent
                startActivity(irASearch);

                // vamos a generar un toast para notificar al usuario
                Toast.makeText(LogIn.this, "¡Busquemos!", Toast.LENGTH_SHORT).show();

                // Animación de cambio de pantalla
                overridePendingTransition(R.anim.page_in, R.anim.page_out);
            }
        });

        //Ponemos a escuchar al TextView de forgot
        forgot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vamos a generar un intent para ir a la pantalla de Home
                Intent irARecovery = new Intent(LogIn.this, Recovery.class);
                // activamos el intent
                startActivity(irARecovery);

                // vamos a generar un toast para notificar al usuario
                Toast.makeText(LogIn.this, "¡Recuperemosla!", Toast.LENGTH_SHORT).show();

                // Animación de cambio de pantalla
                overridePendingTransition(R.anim.page_in, R.anim.page_out);
            }
        });

        //Ponemos a escuchar al TextView de no tienes cuenta
        ntc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vamos a generar un intent para ir a la pantalla de Home
                Intent irASignUp = new Intent(LogIn.this, SignUp.class);
                // activamos el intent
                startActivity(irASignUp);

                // vamos a generar un toast para notificar al usuario
                Toast.makeText(LogIn.this, "¡Registremonos!", Toast.LENGTH_SHORT).show();

                // Animación de cambio de pantalla
                overridePendingTransition(R.anim.page_in, R.anim.page_out);
            }
        });
    }
}