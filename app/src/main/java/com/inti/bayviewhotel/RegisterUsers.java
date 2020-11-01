package com.inti.bayviewhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class RegisterUsers extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    private TextView banner, forgotPassword;
    private EditText editTextEmailAddress, editTextFullName, editTextPosition, editTextPassword;
    private Button register;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_users);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        editTextEmailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        editTextPosition = (EditText) findViewById(R.id.editTextPosition);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmailAddress.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String position = editTextPosition.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmailAddress.setError("Email is required!");
            editTextEmailAddress.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmailAddress.setError("Please provide valid email! example@mail.com");
            editTextEmailAddress.requestFocus();
            return;
        }

        if(fullName.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if(position.isEmpty()){
            editTextPosition.setError("Position is required!");
            editTextPosition.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPosition.setError("Password is required!");
            editTextPosition.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Password must be 6 characters long at least");
            editTextPassword.requestFocus();
            return;
        }


    }
}