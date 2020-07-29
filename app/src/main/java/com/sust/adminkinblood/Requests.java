package com.sust.adminkinblood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Requests extends AppCompatActivity  {
   // private DatabaseReference ref;
    private ArrayList<Rqst_Helper> rqst_list;
    private RecyclerView recyclerView;
    private AdapterClassRqst adapterClassRqst;
    private FirebaseFirestore dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        // FirebaseDatabase.getInstance().getReference("Tokens").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
      //  ref = FirebaseDatabase.getInstance().getReference().child("Tokens").child("TzewxuRq0pYGf0ORmliUzruV8Qu1").child("token");

        recyclerView = findViewById(R.id.R_rv);
        rqst_list = new ArrayList<>();
        dbr = FirebaseFirestore.getInstance();

        /*ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);*/

        dbr.collection("Requests").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    ArrayList<DocumentSnapshot> myList = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot r: myList) {

                        Rqst_Helper rqst = r.toObject(Rqst_Helper.class);
                        Toast.makeText(Requests.this,"Document exists",Toast.LENGTH_SHORT).show();

                        rqst_list.add(rqst);
                    }
                    adapterClassRqst.notifyDataSetChanged();

                } else{
                    Toast.makeText(Requests.this,"Document doesn't exist",Toast.LENGTH_SHORT).show();

                }
            }
        });

        adapterClassRqst = new AdapterClassRqst(this, rqst_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterClassRqst);

        /*if (ref != null) {
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

        }*/



    }





}