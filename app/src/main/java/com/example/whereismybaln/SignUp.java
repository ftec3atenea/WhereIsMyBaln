package com.example.whereismybaln;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Aquí vamos a enlazar el mundo visual con el mundo lógico
        Button btn_signup3 = findViewById(R.id.btn_signup3);
        TextView ytc3 = findViewById(R.id.ytc3);
        CheckBox cb3 = findViewById(R.id.cb3);

        //Ponemos a escuchar al botón de signup
        btn_signup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( cb3.isChecked() ) {
                    // vamos a generar un intent para ir a la pantalla de Home
                    Intent irALogIn = new Intent(SignUp.this, LogIn.class);
                    // activamos el intent
                    startActivity(irALogIn);
                    // vamos a generar un toast para notificar al usuario
                    Toast.makeText(SignUp.this, "¡Cool!", Toast.LENGTH_SHORT).show();
                    // Animación de cambio de pantalla
                    overridePendingTransition(R.anim.page_in, R.anim.page_out);
                } else {
                    // Vamos a solicitar que acepte los términos y condiciones
                    Toast.makeText(SignUp.this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Ponemos a escuchar al TextView de ya tienes cuenta
        ytc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vamos a generar un intent para ir a la pantalla de Home
                Intent irALogIn = new Intent(SignUp.this, LogIn.class);
                // activamos el intent
                startActivity(irALogIn);

                // vamos a generar un toast para notificar al usuario
                Toast.makeText(SignUp.this, "Ingresemos", Toast.LENGTH_SHORT).show();

                // Animación de cambio de pantalla
                overridePendingTransition(R.anim.page_in, R.anim.page_out);
            }
        });
    }
}