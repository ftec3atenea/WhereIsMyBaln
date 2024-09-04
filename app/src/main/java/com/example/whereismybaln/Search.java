package com.example.whereismybaln;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Search extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap gmMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instanciamos las variables
        ImageButton fav6 = findViewById(R.id.fav6);
        ImageButton logOut6 = findViewById(R.id.logOut6);
        Button search = findViewById(R.id.search6);

        // Creamos la animación de vibración
        Animation vibrate = AnimationUtils.loadAnimation(this, R.anim.vibrate);

        // Le indicamos a Android Studio que ya tenemos un mapa en el layout
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        // Permitimos los cambios de vista del mapa
        mapFragment.getMapAsync(this);

        // Añadimos un listener al boton de favoritos
        fav6.setOnClickListener(v -> {
            // Creamos un intent para ir a la actividad de favoritos
            Intent intent = new Intent(this, Favorites.class);
            // Iniciamos la actividad
            startActivity(intent);
            // Le indicamos al usuario que ha pulsado el boton
            Toast.makeText(this, "Veamos tus favoritos", Toast.LENGTH_SHORT).show();

            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
        logOut6.setOnClickListener(v -> {
            // Creamos un intent para ir a la actividad de logOut
            Intent intent = new Intent(this, logOut.class);
            // Iniciamos la actividad
            startActivity(intent);
            // Le indicamos al usuario que ha pulsado el boton
            Toast.makeText(this, "Hasta pronto", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
        search.setOnClickListener(v -> {
            // vibracion
            search.startAnimation(vibrate);
            // Le indicamos al usuario que ha pulsado el boton
            Toast.makeText(this, "¿Qué hay pa'cer?", Toast.LENGTH_SHORT).show();
            //
        });
    }

    @Override
    // Metodo que se ejecuta cuando el mapa esta listo
    public void onMapReady(GoogleMap googleMap) {
        // Guardamos el mapa en una variable
        gmMap = googleMap;
        // Creamos una posicion
        LatLng simoncho = new LatLng(4.658056, -74.093889);
        // Añadimos un marcador en esa posicion (flechita roja)
        gmMap.addMarker(new MarkerOptions().position(simoncho).title("Simoncho"));
        // Movemos el mapa a esa posicion
        gmMap.moveCamera(CameraUpdateFactory.newLatLng(simoncho));
    }
}