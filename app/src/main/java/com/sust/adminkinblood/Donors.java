package com.sust.adminkinblood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.SoundEffectConstants;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Donors extends AppCompatActivity  {


    //private DatabaseReference ref;
    private ArrayList<Dnr_Healper> dnr_list = new ArrayList<>();;
    private RecyclerView recyclerView;
    private androidx.appcompat.widget.SearchView searchView;

    private AdapterClass adapterClass;
    private FirebaseFirestore dbd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors);


        /*ref = FirebaseDatabase.getInstance().getReference().child("Users");*/

        dbd = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.search);
//        adapterClass =new AdapterClass(this,dnr_list);
//        recyclerView.setAdapter(adapterClass);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));






        dbd.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    ArrayList<DocumentSnapshot> myList = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d: myList)
                    {
                       Dnr_Healper dnr = d.toObject(Dnr_Healper.class);

                   //  if(dnr.getDonorStatus() != null)

                         if(dnr.getDonorStatus().equals("positive"))
                       {
                          // Toast.makeText(Donors.this,"positive held",Toast.LENGTH_SHORT).show();
                           dnr_list.add(dnr);
                       }
                    }
                    adapterClass.notifyDataSetChanged();
                    String size =String.valueOf(dnr_list.size()) ;
                    //Toast.makeText(Donors.this,size,Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Donors.this,"Document does not exist",Toast.LENGTH_SHORT).show();
                }
            }
        });





        adapterClass =new AdapterClass(this,dnr_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterClass);


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



            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {

              // adapterClass.getFilter().filter(s);

                   search(s);

                  //  Toast.makeText(Donors.this,"In",Toast.LENGTH_SHORT).show();
                    return false
                            ;
                }

              private void search(String str) {

                     //  String place = "" ;
                  ArrayList<Dnr_Healper> mylist = new ArrayList<>();

//                     for(int i = 0 ; i<str.length();i++){
//                         if(str.charAt(i) == '+' || str.charAt(i) ==  '-') { place = str.substring(i+1);
//                         break;}
//
//                     }
//
//                   //  place.toLowerCase().trim();


                        for (Dnr_Healper object : dnr_list) {
                            if (object.getBloodGroup().toLowerCase().contains(str.toLowerCase())) {
                                mylist.add(object);
                            }
                        }



                    AdapterClass adapterClass = new AdapterClass(Donors.this,mylist);
                    recyclerView.setAdapter(adapterClass);

                }





            });
        }


    }

