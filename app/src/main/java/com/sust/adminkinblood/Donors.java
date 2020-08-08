package com.sust.adminkinblood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.Manifest.permission.CALL_PHONE;

public class Donors extends AppCompatActivity implements AdapterClass.OnListListener {


    //private DatabaseReference ref;
    private ArrayList<Dnr_Healper> dnr_list = new ArrayList<>();;
    private RecyclerView recyclerView;
    private androidx.appcompat.widget.SearchView searchView;

    private AdapterClass adapterClass;
    private FirebaseFirestore dbd;
    private Dialog donorinfo;
    private String uid;
    private String requesterUidForDonor_;


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


        donorinfo = new Dialog(this);



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





        adapterClass =new AdapterClass(this,dnr_list, this);
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


                    AdapterClass adapterClass = new AdapterClass(Donors.this,mylist, Donors.this);
                    recyclerView.setAdapter(adapterClass);

                }


            });
        }


    @Override
    public void OnListClick(int position) {
        showDonorDialog(position);
    }

    private void showDonorDialog(int i) {
        donorinfo.setContentView(R.layout.dialog_donor);
        donorinfo.setCanceledOnTouchOutside(true);

        Button btn_call = donorinfo.findViewById(R.id.dia_btn_call);
        Button btn_assign = donorinfo.findViewById(R.id.dia_btn_assign_donor);
        Button btn_notify_user = donorinfo.findViewById(R.id.dia_btn_notify_user);

        TextView dnr_name = donorinfo.findViewById(R.id.dia_dnr_nm);
        TextView bld_grp = donorinfo.findViewById(R.id.dia_bld_grp);
        TextView dnr_add = donorinfo.findViewById(R.id.dia_add);
        TextView dnr_num = donorinfo.findViewById(R.id.dia_phn_num);

        dnr_name.setText(dnr_list.get(i).getFullName());
        bld_grp.setText(dnr_list.get(i).getBloodGroup());
        dnr_add.setText(dnr_list.get(i).getAddress());
        dnr_num.setText(dnr_list.get(i).getPhoneNumber());
        uid = dnr_list.get(i).getUID();

        btn_call.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(Donors.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + dnr_list.get(i).getPhoneNumber()));
                startActivity(intent);
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{CALL_PHONE},401);
                }
            }
        });

        btn_assign.setOnClickListener(view -> {
            EditText requesterUidForDonor = donorinfo.findViewById(R.id.requester_uid_for_donor);
            requesterUidForDonor_ = requesterUidForDonor.getText().toString();
            if (requesterUidForDonor_.isEmpty()){
                requesterUidForDonor.setError("Field cannot be empty");
                requesterUidForDonor.requestFocus();
                return;
            }
            dbd.collection("Requests").document(requesterUidForDonor_).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()){
                        Rqst_Helper rqst_helper = documentSnapshot.toObject(Rqst_Helper.class);
                        if (rqst_helper != null){
                            dbd.collection("Confirmed_Requests")
                                    .document(uid)
                                    .set(rqst_helper)
                                    .addOnSuccessListener(aVoid -> Toast.makeText(Donors.this, "Donor Assigned", Toast.LENGTH_SHORT).show())
                                    .addOnFailureListener(e -> Toast.makeText(Donors.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                        } else {
                            Toast.makeText(Donors.this, "Error Occured!!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Donors.this, "Request Doesnt exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(e -> Toast.makeText(Donors.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        btn_notify_user.setOnClickListener(view -> {

            Map<String, Object> hm = new HashMap<>();
            hm.put("donorName", dnr_list.get(i).getFullName());
            hm.put("donorPhoneNumber", dnr_list.get(i).getPhoneNumber());

            dbd.collection("Requests")
                    .document(requesterUidForDonor_)
                    .set(hm, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> {
                        Map<String, Object> hashMap = new HashMap<>();
                        hashMap.put("requestStatus", "positive");

                        dbd.collection("Users")
                                .document(requesterUidForDonor_)
                                .set(hashMap)
                                .addOnSuccessListener(aVoid1 -> Toast.makeText(this, "User Notified", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());

                    }).addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        donorinfo.show();
        Objects.requireNonNull(donorinfo.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}

