package com.sust.adminkinblood;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
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
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import static android.Manifest.permission.CALL_PHONE;

public class Requests extends AppCompatActivity implements AdapterClassRqst.OnListListenerRqst {
   // private DatabaseReference ref;
    private ArrayList<Rqst_Helper> rqst_list;
    private AdapterClassRqst adapterClassRqst;
    private FirebaseFirestore dbr;
    private Dialog requestInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        // FirebaseDatabase.getInstance().getReference("Tokens").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
      //  ref = FirebaseDatabase.getInstance().getReference().child("Tokens").child("TzewxuRq0pYGf0ORmliUzruV8Qu1").child("token");

        RecyclerView recyclerView = findViewById(R.id.R_rv);
        rqst_list = new ArrayList<>();
        dbr = FirebaseFirestore.getInstance();

        requestInfo = new Dialog(this);

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

        adapterClassRqst = new AdapterClassRqst(this, rqst_list, this);
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


    @Override
    public void OnListClickRqst(int position) {
        showRequestDialog(position);
    }

    private void showRequestDialog(int i) {

        requestInfo.setContentView(R.layout.dialog_request);
        requestInfo.setCanceledOnTouchOutside(true);

        Button btn_confirm = requestInfo.findViewById(R.id.R_dia_btn_confirm);
        Button btn_call = requestInfo.findViewById(R.id.R_dia_btn_call);

        EditText r_requester_uid = requestInfo.findViewById(R.id.requester_uid);
        TextView r_hospital = requestInfo.findViewById(R.id.R_dia_hospital);
        TextView r_address = requestInfo.findViewById(R.id.R_dia_address);
        TextView r_bld_grp = requestInfo.findViewById(R.id.R_dia_bld_grp);
        TextView r_nobags = requestInfo.findViewById(R.id.R_dia_no_ofbags);
        TextView r_num = requestInfo.findViewById(R.id.R_dia_phn_num);
        TextView r_condition = requestInfo.findViewById(R.id.R_dia_condition);
        TextView r_name=requestInfo.findViewById(R.id.R_dia_name);
        TextView r_text=requestInfo.findViewById(R.id.R_dia_text);
        TextView r_time=requestInfo.findViewById(R.id.R_dia_time);


        String donorHaveToGoLocationName = rqst_list.get(i).getDonorHaveToGoLocationName();
        String donorHaveToGoLocationAddress = rqst_list.get(i).getDonorHaveToGoLocationAddress();
        String bloodGroup = rqst_list.get(i).getBloodGroup();
        String noOfBags = rqst_list.get(i).getNoOfBags();
        String phoneNumber = rqst_list.get(i).getPhoneNumber();
        String fullName = rqst_list.get(i).getFullName();
        String condition = rqst_list.get(i).getCondition();
        String time = rqst_list.get(i).getTime();
        String text = rqst_list.get(i).getText();

        r_hospital.setText(donorHaveToGoLocationName);
        r_address.setText(donorHaveToGoLocationAddress);
        r_bld_grp.setText(bloodGroup);
        r_nobags.setText(noOfBags);
        r_num.setText(phoneNumber);
        r_name.setText(fullName);
        r_condition.setText(condition);
        r_text.setText(text);
        r_time.setText(time);


        btn_call.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(Requests.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + rqst_list.get(i).getPhoneNumber()));
                startActivity(intent);
            }
            else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{CALL_PHONE},401);
                }
            }
        });

        btn_confirm.setOnClickListener(view -> {
            Map<String, Object> hm = new HashMap<>();
            hm.put("requestStatus", "initialConfirmed");

            dbr.collection("Users")
                    .document(rqst_list.get(i).getUid())
                    .set(hm, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> {
                        r_requester_uid.setText(rqst_list.get(i).getUid());
                    }).addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show());
        });


        requestInfo.show();
        Objects.requireNonNull(requestInfo.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}