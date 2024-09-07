package com.example.whereismybaln;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {
    FirebaseAuth auth1;
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

        // Instanciamos el objeto FirebaseAuth para poder usar el login de Firebase
        auth1 = FirebaseAuth.getInstance();

        //Aquí vamos a enlazar el mundo visual con el mundo lógico
        EditText input_user2 = findViewById(R.id.input_user2);
        EditText input_pass2 = findViewById(R.id.input_pass2);
        Button btn_login2 = findViewById(R.id.btn_login2);
        TextView forgot2 = findViewById(R.id.forgot2);
        TextView ntc2 = findViewById(R.id.ntc2);

        //Ponemos a escuchar al botón de login
        btn_login2.setOnClickListener(v -> {
            // casteamos los datos a String
            String user = input_user2.getText().toString().trim();
            String pass = input_pass2.getText().toString().trim();
            if ( !user.isEmpty() && !pass.isEmpty() ) {
                // llamamos al método loginUser
                loginUser(user, pass);
            } else {
                // vamos a generar un toast para notificar al usuario
                Toast.makeText(LogIn.this, "¡Ojo que no podemos dejar campos vacíos!", Toast.LENGTH_SHORT).show();
            }
        });

        //Ponemos a escuchar al TextView de forgot
        forgot2.setOnClickListener(v -> {
            // vamos a generar un intent para ir a la pantalla de Home
            Intent irARecovery = new Intent(LogIn.this, Recovery.class);
            // activamos el intent
            startActivity(irARecovery);
            // vamos a generar un toast para notificar al usuario
            Toast.makeText(LogIn.this, "¡Recuperemosla!", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });

        //Ponemos a escuchar al TextView de no tienes cuenta
        ntc2.setOnClickListener(v -> {
            // vamos a generar un intent para ir a la pantalla de Home
            Intent irASignUp = new Intent(LogIn.this, SignUp.class);
            // activamos el intent
            startActivity(irASignUp);
            // vamos a generar un toast para notificar al usuario
            Toast.makeText(LogIn.this, "¡Registremonos!", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
    }

    // Método para iniciar sesión
    private void loginUser(String user, String pass) {
        // Iniciamos sesión con el método signInWithEmailAndPassword
        auth1.signInWithEmailAndPassword(user, pass).addOnCompleteListener(task -> { // Si la tarea es exitosa
            // Hacemos un condicional para saber si la tarea fue exitosa
            if (task.isSuccessful()){ // Si se logra iniciar sesión
                // vamos a generar un toast para notificar al usuario
                Toast.makeText(LogIn.this, "¡Busquemos!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogIn.this, Search.class));
                Toast.makeText(LogIn.this, "Bienvenido " + user.toString(), Toast.LENGTH_SHORT).show();
                // Animación de cambio de pantalla
                overridePendingTransition(R.anim.page_in, R.anim.page_out);
                finish();
            }else{ // Si no se logra iniciar sesión
                Toast.makeText(LogIn.this, "Datos Incorrectos", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> // Si la tarea falla
                Toast.makeText(LogIn.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
        );
    }

    // Método para verificar si el usuario ya está logueado
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth1.getCurrentUser();
        if (user != null){ // Si el usuario ya está logueado, pasa directo a la pantalla de Search
            startActivity(new Intent(LogIn.this, Search.class));
            finish();
        }
    }
}