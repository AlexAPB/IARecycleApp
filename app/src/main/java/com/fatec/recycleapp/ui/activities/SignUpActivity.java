package com.fatec.recycleapp.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fatec.recycleapp.R;
import com.fatec.recycleapp.ui.fragments.sign.SignUpAddress;
import com.fatec.recycleapp.ui.fragments.sign.SignUpType;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SignUpType())
                .commit();
    }
}
