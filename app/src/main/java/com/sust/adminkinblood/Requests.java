package com.sust.adminkinblood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Requests extends AppCompatActivity {
    private DatabaseReference ref;
    private ArrayList<Rqst_Helper> rqst_list;
    private RecyclerView recyclerView;
    private AdapterClassRqst adapterClassRqst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        // FirebaseDatabase.getInstance().getReference("Tokens").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        ref = FirebaseDatabase.getInstance().getReference().child("Tokens").child("TzewxuRq0pYGf0ORmliUzruV8Qu1").child("token");
        recyclerView = findViewById(R.id.R_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterClassRqst = new AdapterClassRqst(this, rqst_list);
        recyclerView.setAdapter(adapterClassRqst);

        if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        rqst_list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            rqst_list.add(ds.getValue(Rqst_Helper.class));
                        }
                        AdapterClassRqst adapterClassRqst = new AdapterClassRqst(getApplicationContext(),rqst_list);
                        recyclerView.setAdapter(adapterClassRqst);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Requests.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}