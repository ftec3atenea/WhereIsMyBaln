package com.example.whereismybaln;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Intanciamos la variable, enlazamos el mundo visual con la lógica
        Button btnLogin1 = findViewById(R.id.btn_login1);
        Button btnSignup1 = findViewById(R.id.btn_signup1);

        //Ponemos en escucha a los botones
        btnLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creamos el intento de cambio de pantalla
                Intent irALogin = new Intent(MainActivity.this, LogIn.class);
                // ejecutamos el cambio de pantalla
                startActivity(irALogin);

                // vamos a mostrar un mensaje de confirmación
                Toast.makeText(MainActivity.this, "¡Ahora si viene lo chido!", Toast.LENGTH_SHORT).show();

                // Animación de cambio de pantalla
                overridePendingTransition(R.anim.page_in, R.anim.page_out);
            }
        });

        btnSignup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creamos el intento de cambio de pantalla
                Intent irASignUp = new Intent(MainActivity.this, SignUp.class);
                // ejecutamos el cambio de pantalla
                startActivity(irASignUp);

                // vamos a mostrar un mensaje de confirmación
                Toast.makeText(MainActivity.this, "¡Empezó la diversión!", Toast.LENGTH_SHORT).show();

                // Animación de cambio de pantalla
                overridePendingTransition(R.anim.page_in, R.anim.page_out);
            }
        });
    }
}