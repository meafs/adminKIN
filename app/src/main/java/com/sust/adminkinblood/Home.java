package com.sust.adminkinblood;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sust.adminkinblood.notification.Token;


public class Home extends AppCompatActivity {

    public FirebaseAuth FIREBASE_AUTH;
    public FirebaseUser FIREBASE_USER;
    public DocumentReference DOCUMENT_REFERENCE;
    public FirebaseFirestore db;


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setTitle("Confirm exit").setMessage("Are you sure you want to exit?").setPositiveButton("Yes", (dialog, which) -> finish()).setNegativeButton("No", (dialog, which) -> dialog.dismiss());
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

        Button btn_findDonor = findViewById(R.id.Bfind_donor);
        Button btn_seeRqst = findViewById(R.id.Bsee_rqst);
        Button btn_logout = findViewById(R.id.Blogout);
        //btn_findDonor.setVisibility(View.INVISIBLE);


        btn_logout.setOnClickListener(v -> {
            FIREBASE_AUTH.signOut();
            Toast.makeText(Home.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Home.this, MainActivity.class));
            finish();
        });
        btn_findDonor.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, DonorSearchActivity.class);
            startActivity(intent);
        });

        btn_seeRqst.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, Requests.class);
            startActivity(intent);
        });


        //Button send = findViewById(R.id.btn_send);

        /*send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title_ = title.getText().toString().trim();
                message_ = message.getText().toString().trim();
                FirebaseDatabase.getInstance().getReference().child("Tokens").child("ONEJp9mzAIdaetfvK7C3d4tcRGH2").child("token").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String usertoken = dataSnapshot.getValue(String.class);
                        sendNotifications(usertoken, title_, message_);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });*/
        updateToken();
    }

    private void updateToken() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Token token = new Token(refreshToken);
        assert firebaseUser != null;
        FirebaseDatabase.getInstance().getReference("Tokens").child("Admins").child(firebaseUser.getUid()).setValue(token);
    }
}
