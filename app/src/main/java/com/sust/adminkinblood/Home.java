package com.sust.adminkinblood;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home extends AppCompatActivity {

    private Button btn_findDonor, btn_seeRqst,btn_ntf,btn_logout;
    public static FirebaseAuth FIREBASE_AUTH;
    public static FirebaseUser FIREBASE_USER;
    public static DocumentReference DOCUMENT_REFERENCE;
    public static FirebaseFirestore db;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setTitle("Confirm exit").setMessage("Are you sure you want to exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FIREBASE_AUTH = FirebaseAuth.getInstance();
        FIREBASE_USER = FIREBASE_AUTH.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        DOCUMENT_REFERENCE = db.collection("Users").document(FIREBASE_USER.getUid());

        btn_findDonor = findViewById(R.id.Bfind_donor);
        btn_seeRqst = findViewById(R.id.Bsee_rqst);
        btn_ntf = findViewById(R.id.Bntf);
        btn_logout = findViewById(R.id.Blogout);



        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FIREBASE_AUTH.signOut();
                Toast.makeText(Home.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Home.this, MainActivity.class));
                finish();
            }
        });
        btn_findDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Donors.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        btn_ntf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Notification.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btn_seeRqst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, Requests.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
