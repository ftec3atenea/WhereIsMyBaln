package com.example.whereismybaln;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    // Instanciamos las variables globales para usar Firebase
    FirebaseFirestore db1; // Para usar la base de datos de Firebase
    FirebaseAuth auth1; // Para usar la autenticación de Firebase

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

        // Instanciamos las variables usar Firebase
        db1 = FirebaseFirestore.getInstance();
        auth1 = FirebaseAuth.getInstance();

        // Aquí vamos a enlazar el mundo visual con el mundo lógico
        EditText user3 = findViewById(R.id.input_user3);
        EditText name3 = findViewById(R.id.input_name3);
        EditText email3 = findViewById(R.id.input_email3);
        EditText pass3 = findViewById(R.id.input_pass3);
        EditText confirm3 = findViewById(R.id.confirm_pass3);
        Button btn_signup3 = findViewById(R.id.btn_signup3);
        TextView ytc3 = findViewById(R.id.ytc3);
        CheckBox cb3 = findViewById(R.id.cb3);

        // Ponemos a escuchar al botón de signup
        btn_signup3.setOnClickListener(v -> {
            if ( cb3.isChecked() ) {
                String user = user3.getText().toString().trim();
                String name = name3.getText().toString().trim();
                String email = email3.getText().toString().trim();
                String pass = pass3.getText().toString().trim();
                String confirm = confirm3.getText().toString().trim();

                // Comprobamos que los campos no estén vacíos
                if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !user.isEmpty()){
                    // Comprobamos que las contraseñas sean iguales
                    if (pass.equals(confirm)){
                        // Comprobamos que la contraseña tenga más de 6 caracteres
                        if (pass.length() >= 6){
                            // Comprobamos que el email tenga el formato correcto
                            if (email.contains("@") && email.contains(".")){
                                // Llamamos al método registerUser, para registrar al usuario
                                registerUser(user, name, email, pass);
                            } else {
                                Toast.makeText(SignUp.this, "El email no es válido", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUp.this, "La contraseña debe tener más de 6 caracteres", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUp.this, "Completa todos los datos", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Vamos a solicitar que acepte los términos y condiciones
                Toast.makeText(SignUp.this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
            }
        });

        //Ponemos a escuchar al TextView de ya tienes cuenta
        ytc3.setOnClickListener(v -> {
            // vamos a generar un intent para ir a la pantalla de Home
            Intent irALogIn = new Intent(SignUp.this, LogIn.class);
            // activamos el intent
            startActivity(irALogIn);
            // vamos a generar un toast para notificar al usuario
            Toast.makeText(SignUp.this, "Ingresemos", Toast.LENGTH_SHORT).show();
            // Animación de cambio de pantalla
            overridePendingTransition(R.anim.page_in, R.anim.page_out);
        });
    }

    // Método para registrar al usuario
    private void registerUser(String user, String name, String email, String pass) {
        // Llamamos al método createUserWithEmailAndPassword de Firebase
        auth1.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
            // Traemos el siguiente ID disponible en la base de datos
            String id = auth1.getCurrentUser().getUid();
            // Creamos un mapa (Formato Base de datos) con los datos del usuario
            Map<String, Object> userMap = new HashMap<>();
            // Añadimos los datos al mapa ("campo", valor del campo)
            userMap.put("id", id);
            userMap.put("user", user);
            userMap.put("name", name);
            userMap.put("email", email);
            userMap.put("pass", pass);

            // Guardamos los datos en la base de datos
            db1.collection("users1").document(id)
                    .set(userMap)
                    .addOnSuccessListener(unused -> {
                        // vamos a generar un intent para ir a la pantalla de Home
                        Intent irALogIn = new Intent(SignUp.this, LogIn.class);
                        // activamos el intent
                        startActivity(irALogIn);
                        // vamos a generar un toast para notificar al usuario
                        Toast.makeText(SignUp.this, "¡Cool!", Toast.LENGTH_SHORT).show();
                        // Animación de cambio de pantalla
                        overridePendingTransition(R.anim.page_in, R.anim.page_out);
                        finish();

                    }).addOnFailureListener(e -> // Si hay un error al guardar los datos
                            Toast.makeText(SignUp.this, "Error al guardar", Toast.LENGTH_SHORT).show());

        }).addOnFailureListener(e -> // Si hay un error al registrar al usuario
                Toast.makeText(SignUp.this, "Error al registrarte", Toast.LENGTH_SHORT).show());
    }
}