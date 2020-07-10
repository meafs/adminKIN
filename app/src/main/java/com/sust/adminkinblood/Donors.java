package com.sust.adminkinblood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Donors extends AppCompatActivity  {


    //private DatabaseReference ref;
    private ArrayList<Dnr_Healper> dnr_list;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private AdapterClass adapterClass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);


        /*ref = FirebaseDatabase.getInstance().getReference().child("Users");*/

        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterClass =new AdapterClass(this,dnr_list);
        recyclerView.setAdapter(adapterClass);

        Home.db.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    ArrayList<DocumentSnapshot> myList = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d: myList)
                    {
                       Dnr_Healper dnr =d.toObject(Dnr_Healper.class);
                       if(dnr.getDonarstatus().equals("positive"))
                       {
                           dnr_list.add(dnr);
                       }
                    }
                    adapterClass.notifyDataSetChanged();

                }
            }
        });





        


        /*if (ref != null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(Dnr_Healper.class));
                        }
                        AdapterClass adapterClass = new AdapterClass(getApplicationContext(), list);
                        recyclerView.setAdapter(adapterClass);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Donors.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }*/

        if(searchView!=null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }

                private void search(String str) {
                ArrayList<Dnr_Healper> mylist = new ArrayList<>();
                for (Dnr_Healper object : dnr_list)
                {
                    if(object.getBloodGroup().toLowerCase().contains(str.toLowerCase()))
                    {
                        mylist.add(object);
                    }
                }
                AdapterClass adapterClass = new AdapterClass(mylist);
                recyclerView.setAdapter(adapterClass);

                }
            });
        }
    }
}