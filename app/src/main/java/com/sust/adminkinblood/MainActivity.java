package com.sust.adminkinblood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    EditText txtemail, txtpassword;
    Button btnlogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtemail = findViewById(R.id.txt_email);
        txtpassword = findViewById(R.id.txt_password);
        btnlogin = findViewById(R.id.btn_login);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        btnlogin.setOnClickListener(v -> {
            String email = txtemail.getText().toString().trim();
            String password = txtpassword.getText().toString().trim();

            if(TextUtils.isEmpty(email))
            {
                Toast.makeText(MainActivity.this, "Email Cannot be Empty", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password))
            {
                Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                Toast.makeText(MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            progressDialog.setMessage("Loggin In");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, task -> {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }

    }
}
