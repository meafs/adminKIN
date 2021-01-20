package com.sust.adminkinblood.adapters;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.sust.adminkinblood.Dnr_Healper;
import com.sust.adminkinblood.R;
import com.sust.adminkinblood.Rqst_Helper;
import com.sust.adminkinblood.notification.APIService;
import com.sust.adminkinblood.notification.Data;
import com.sust.adminkinblood.notification.MyResponse;
import com.sust.adminkinblood.notification.NotificationSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CALL_PHONE;

public class DonorSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Dnr_Healper> dnr_healpers;
    private Context context;
    private Dialog donorinfo;
    private CollectionReference COLLECTION_REFERENCE_USERS, COLLECTION_REFERENCE_REQUESTS, COLLECTION_REFERENCE_DONORDATAFORUSER, COLLECTION_REFERENCE_CONFIRMED_REQUESTS;
    private FirebaseFirestore DATABASE_REFERENCE;
    private String uid;
    private String requesterUidForDonor_;
    private APIService apiService;


    public DonorSearchAdapter(ArrayList<Dnr_Healper> dnr_healpers, Context context, Dialog donorinfo) {
        this.dnr_healpers = dnr_healpers;
        this.context = context;
        this.donorinfo = donorinfo;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donor, parent,false);


        return new DonorSearchAdapter.VHItem(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String dnr_name = "", bld_grp = "", phn_num = "";


        final DonorSearchAdapter.VHItem vhItem = (DonorSearchAdapter.VHItem) holder;

        dnr_name = dnr_healpers.get(position).getFullName();
        bld_grp = dnr_healpers.get(position).getBloodGroup();
        phn_num = dnr_healpers.get(position).getPhoneNumber();


        vhItem.dnr_Name.setText(dnr_name);
        vhItem.bld_grp.setText(bld_grp);
        vhItem.phn_num.setText(phn_num);

        vhItem.item_Donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDonorDialog(position, dnr_healpers);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dnr_healpers.size();
    }

    class VHItem extends RecyclerView.ViewHolder{

        private TextView dnr_Name,bld_grp,phn_num;
        private LinearLayout item_Donor;

        public VHItem(@NonNull View itemView) {
            super(itemView);

            item_Donor= itemView.findViewById(R.id.Dnr_id);;
            dnr_Name = itemView.findViewById(R.id.dnr_name);
            bld_grp = itemView.findViewById(R.id.bld_grp);
            phn_num= itemView.findViewById(R.id.phn_num);
        }
    }




    private void showDonorDialog(int i, ArrayList<Dnr_Healper> list) {

        donorinfo.setContentView(R.layout.dialog_donor);
        donorinfo.setCanceledOnTouchOutside(true);

        Button btn_call = donorinfo.findViewById(R.id.dia_btn_call);
        Button btn_assign = donorinfo.findViewById(R.id.dia_btn_assign_donor);
        Button btn_notify_user = donorinfo.findViewById(R.id.dia_btn_notify_user);

        TextView dnr_name = donorinfo.findViewById(R.id.dia_dnr_nm);
        TextView bld_grp = donorinfo.findViewById(R.id.dia_bld_grp);
        TextView dnr_add = donorinfo.findViewById(R.id.dia_add);
        TextView dnr_num = donorinfo.findViewById(R.id.dia_phn_num);

        dnr_name.setText(list.get(i).getFullName());
        bld_grp.setText(list.get(i).getBloodGroup());
        dnr_add.setText(list.get(i).getCurrentLocationAddress());
        dnr_num.setText(list.get(i).getPhoneNumber());
        uid = list.get(i).getUid();

        btn_call.setOnClickListener(view -> {
            if (ActivityCompat.checkSelfPermission(context, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + list.get(i).getPhoneNumber()));
                context.startActivity(intent);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        ActivityCompat.requestPermissions((AppCompatActivity) context,
                                new String[]{Manifest.permission.CALL_PHONE}, 1000);
                    }
            }
        });

        btn_assign.setOnClickListener(view -> {
            EditText requesterUidForDonor = donorinfo.findViewById(R.id.requester_uid_for_donor);
            requesterUidForDonor_ = requesterUidForDonor.getText().toString();
            if (requesterUidForDonor_.isEmpty()) {
                requesterUidForDonor.setError("Field cannot be empty");
                requesterUidForDonor.requestFocus();
                return;
            }
            COLLECTION_REFERENCE_REQUESTS.document(requesterUidForDonor_).get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    Rqst_Helper rqst_helper = documentSnapshot.toObject(Rqst_Helper.class);
                    if (rqst_helper != null) {
                        COLLECTION_REFERENCE_CONFIRMED_REQUESTS
                                .document(uid)
                                .set(rqst_helper, SetOptions.merge())
                                .addOnSuccessListener(aVoid -> FirebaseFirestore.getInstance()
                                        .collection("Users")
                                        .document(uid).update("donating", true)
                                        .addOnSuccessListener(aVoid1 -> {

                                            FirebaseDatabase.getInstance().getReference().child("Tokens").child(uid).addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    Map<String, String> hm = (Map<String, String>) snapshot.getValue();
                                                    assert hm != null;
                                                    sendNotifications(hm.get("token"), "assign");
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show())).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
                    } else {
                        Toast.makeText(context, "Error Occured!!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Request Doesnt exist", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
        });

        btn_notify_user.setOnClickListener(view -> COLLECTION_REFERENCE_REQUESTS
                .document(requesterUidForDonor_)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    Map<String, Object> hm = new HashMap<>();
                    hm.put("donorHaveToGoLatitude", documentSnapshot.getDouble("donorHaveToGoLatitude"));
                    hm.put("donorHaveToGoLongitude", documentSnapshot.getDouble("donorHaveToGoLongitude"));
                    hm.put("donorName", list.get(i).getFullName());
                    hm.put("donorPhoneNumber", list.get(i).getPhoneNumber());
                    hm.put("donorUID", uid);

                    COLLECTION_REFERENCE_DONORDATAFORUSER
                            .document(requesterUidForDonor_)
                            .set(hm, SetOptions.merge())
                            .addOnSuccessListener(aVoid12 -> COLLECTION_REFERENCE_REQUESTS
                                    .document(requesterUidForDonor_)
                                    .delete().addOnSuccessListener(aVoid1 -> {
                                        Map<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("requestStatus", "positive");
                                        COLLECTION_REFERENCE_USERS
                                                .document(requesterUidForDonor_)
                                                .set(hashMap, SetOptions.merge())
                                                .addOnSuccessListener(aVoid2 -> {
                                                    FirebaseDatabase.getInstance().getReference().child("Tokens").child(requesterUidForDonor_).addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            Map<String, String> hm = (Map<String, String>) snapshot.getValue();
                                                            assert hm != null;
                                                            sendNotifications(hm.get("token"), "notify");
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                                }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
                                    }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show()))
                            .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
                }).addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show()));

        donorinfo.show();
        Objects.requireNonNull(donorinfo.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void sendNotifications(String token, String message) {
        Log.d("tikka", token);
        Data data = new Data(message);
        NotificationSender sender = new NotificationSender(data, token);
        apiService.sendNotification(sender).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        if (response.body().success != 1) {
                            Toast.makeText(context, "Response body error", Toast.LENGTH_SHORT).show();
                        } else {
                            if (message.equals("assign")) {
                                Toast.makeText(context, "Donor Assigned", Toast.LENGTH_SHORT).show();
                            } else if (message.equals("notify")) {
                                Toast.makeText(context, "User Notified", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(context, "Response body null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Response code error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {

            }
        });
    }


}
