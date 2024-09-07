package com.example.whereismybaln;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Vamos a unir la variable del mundo visual con la variable del mundo lógico
        ImageView splash = findViewById(R.id.fondo0);
        TextView titulo0 = findViewById(R.id.titulo0);

        //Vamos a crear la animación
        Animation anim_set = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.set_in_out);
        //Vamos a ejecutar la animación
        titulo0.startAnimation(anim_set);

        //Creamos programaticamente la animación
        Animation fadeIn = new AlphaAnimation(0,1);
        //Establecemos la duración de la animación
        fadeIn.setDuration(2000);
        //Establecemos la animación al ImageView (Splash)
        splash.startAnimation(fadeIn);

        splash.postDelayed(new Runnable() { // Metodo para ejecutar la animación
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}