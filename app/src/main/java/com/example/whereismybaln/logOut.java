package com.example.whereismybaln;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class logOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_out);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instanciamos las variables
        Button btn_logOut = findViewById(R.id.btn_logOut);
        Spinner sp8 = findViewById(R.id.sp8);

        //Creo una lista para mostrar las opciones a elegir
        ArrayList<String> Estrellas = new ArrayList<>();
        //Agrego los elementos a mostrar en mi lista
        Estrellas.add("1 pinche Estrella");
        Estrellas.add("2 Estrellas");
        Estrellas.add("3 Estrellitas");
        Estrellas.add("4 Estrelladas");
        Estrellas.add("Un montón de Estrellas");

        //Creo un adaptador para que la lista se muestra en modo menu desplegable
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Estrellas);
        //Ajusto la lista al spinner con un desplazamiento hacia abajo
        sp8.setAdapter(adap);

        // Añadimos un listener al boton de logOut
        btn_logOut.setOnClickListener(v -> {
            // Generamos el intent
            Intent intent = new Intent(this, MainActivity.class);
            // Iniciamos la actividad
            startActivity(intent);
            // Le indicamos al usuario que ha pulsado el boton
            Toast.makeText(this, "Buen viento y buena mar", Toast.LENGTH_SHORT).show();
            // Animacion de cambio de pantallas
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
    }
}