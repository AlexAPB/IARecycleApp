package com.fatec.recycleapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fatec.recycleapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        EditText emailText = findViewById(R.id.ahEmail);
        EditText passwordText = findViewById(R.id.ahPassword);

        Button loginButton = findViewById(R.id.ahLogin);
        Button signButton = findViewById(R.id.ahSign);

        loginButton.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            login(email, password);
        });

        signButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Falha no login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
