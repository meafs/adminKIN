package com.sust.adminkinblood;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

import okhttp3.internal.cache.DiskLruCache;

import static android.Manifest.permission.CALL_PHONE;

public class AdapterClassRqst extends RecyclerView.Adapter<AdapterClassRqst.ViewHolder> {

    Context mContext;
    ArrayList<Rqst_Helper> mData;
    Dialog myDialog;

    public AdapterClassRqst(Context mContext, ArrayList<Rqst_Helper> list) {
        this.mData = list;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_donor, parent, false);
        final ViewHolder vHolder = new ViewHolder(view);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_donor);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView r_hospital = myDialog.findViewById(R.id.R_dia_hospital);
        TextView r_bld_grp = myDialog.findViewById(R.id.R_dia_bld_grp);
        TextView r_nobags = myDialog.findViewById(R.id.R_dia_no_ofbags);
        TextView r_num = myDialog.findViewById(R.id.R_dia_phn_num);
        TextView r_condition = myDialog.findViewById(R.id.R_dia_condition);
        TextView r_name=myDialog.findViewById(R.id.R_dia_name);
        Button btn_call = myDialog.findViewById(R.id.R_call);
        Button btn_share = myDialog.findViewById(R.id.R_share);

        r_hospital.setText(mData.get(vHolder.getAdapterPosition()).getHospital_());
        r_bld_grp.setText(mData.get(vHolder.getAdapterPosition()).getBlood_group());
        r_nobags.setText(mData.get(vHolder.getAdapterPosition()).getNoOfBags_());
        r_num.setText(mData.get(vHolder.getAdapterPosition()).getPhoneNumber());
        r_name.setText(mData.get(vHolder.getAdapterPosition()).getFullName());
        r_condition.setText(mData.get(vHolder.getAdapterPosition()).getCondition_());

        btn_call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(mContext, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + mData.get(i).getPhoneNumber()));
                    ((Activity)mContext).startActivity(intent);
                }
                else {
                    ((Activity)mContext).requestPermissions(new String[]{CALL_PHONE},401);
                }
            }
        });

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share_intent = new Intent(Intent.ACTION_SEND);
                share_intent.setType("text/plain");
                share_intent.putExtra(Intent.EXTRA_TEXT,vHolder.phoneNumber.getText().toString()+"Contact "+mData.get(i));
                share_intent.putExtra(Intent.EXTRA_TEXT,"Hey Could you help here..?");
                ((Activity)mContext).startActivity(Intent.createChooser(share_intent,"SHARE BY"));
            }
        });


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.hospital_.setText(mData.get(i).getHospital_());
        holder.blood_group.setText(mData.get(i).getBlood_group());
        holder.phoneNumber.setText(mData.get(i).getPhoneNumber());
        holder.noOfBags_.setText(mData.get(i).getNoOfBags_());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(mContext, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + mData.get(i).getPhoneNumber()));
                    ((Activity)mContext).startActivity(intent);
                }
                else {
                    ((Activity)mContext).requestPermissions(new String[]{CALL_PHONE},401);
                }
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share_intent = new Intent(Intent.ACTION_SEND);
                share_intent.setType("text/plain");
                share_intent.putExtra(Intent.EXTRA_TEXT,holder.phoneNumber.getText().toString()+"Contact "+mData.get(i));
                share_intent.putExtra(Intent.EXTRA_TEXT,"Hey Could you help here..?");
                ((Activity)mContext).startActivity(Intent.createChooser(share_intent,"SHARE BY"));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void removeRqst(int i) {
        mData.remove(i);
        notifyItemRemoved(i);
    }
    public void restoreRqst(Rqst_Helper rqst,int i) {
        mData.add(i,rqst);
        notifyItemRemoved(i);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView hospital_, noOfBags_, blood_group, phoneNumber;
        private ImageView call, share;
        private LinearLayout item_rqst;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_rqst = itemView.findViewById(R.id.Rqst_id);
            hospital_ = itemView.findViewById(R.id.Rqst_hospital);
            blood_group = itemView.findViewById(R.id.Rqst_bld_grp);
            noOfBags_ = itemView.findViewById(R.id.Rqst_no_of_bags);
            phoneNumber = itemView.findViewById(R.id.Rqst_phn_num);
            call = itemView.findViewById(R.id.R_call);
            share = itemView.findViewById(R.id.R_share);

        }

    }
}