package com.example.whereismybaln;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.viewpager2.widget.ViewPager2;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.tabs.TabLayout;

public class Favorites extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorites);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Instanciamos las variables
        ImageButton search7 = findViewById(R.id.search7);
        ImageButton logOut7 = findViewById(R.id.logOut7);
        TabLayout tabLayout = findViewById(R.id.tl7);
        ViewPager2 viewPager = findViewById(R.id.vp7);

        // Creamos el adaptador para el viewPager (El que gestiona los fragmentos)
        PageController pagerController2 = new PageController(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(pagerController2);

        // Conectamos el tabLayout con el viewPager
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        // Añadimos un listener al tabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Añadimos un listener al boton de favoritos
        search7.setOnClickListener(v -> {
            // Generamos el intent
            Intent intent = new Intent(this, Search.class);
            // Iniciamos la actividad
            startActivity(intent);
            // Le indicamos al usuario que ha pulsado el boton
            Toast.makeText(this, "¿A dónde vas?", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
        // Añadimos un listener al boton de logOut
        logOut7.setOnClickListener(v -> {
            // Generamos el intent
            Intent intent = new Intent(this, logOut.class);
            // Iniciamos la actividad
            startActivity(intent);
            // Le indicamos al usuario que ha pulsado el boton
            Toast.makeText(this, "Hasta pronto", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
    }
}