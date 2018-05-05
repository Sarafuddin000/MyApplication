package org.bitleet.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText editTextName, editTextEmail, editTextMobile, editTextPassword;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextMobile = findViewById(R.id.editTextMobile);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonSignup).setOnClickListener(this);
        findViewById(R.id.buttonGoLogin).setOnClickListener(this);

    }
    private void registerUser(){
        String Name = editTextName.getText().toString().trim();
        String Email = editTextEmail.getText().toString().trim();
        String Mobile = editTextMobile.getText().toString().trim();
        String Password = editTextPassword.getText().toString().trim();

        if (Name.isEmpty()) {
            editTextName.setError("Full name is required");
            editTextName.requestFocus();
            return;
        }

        if (Email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (Mobile.isEmpty()) {
            editTextMobile.setError("Mobile number is required");
            editTextMobile.requestFocus();
            return;
        }


         if (Password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (Password.length()<6){
            editTextPassword.setError("Minimum length of password is 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User register Successfull", Toast.LENGTH_SHORT).show();
                }

             }


        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignup:
                registerUser();
                break;

            case R.id.buttonGoLogin:
                startActivity(new Intent(this, MainActivity.class));

                break;

        }
    }
}
