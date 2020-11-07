package com.sust.adminkinblood;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import okhttp3.internal.cache.DiskLruCache;

import static android.Manifest.permission.CALL_PHONE;

public class AdapterClassRqst extends RecyclerView.Adapter<AdapterClassRqst.ViewHolder> {

    private OnListListenerRqst mOnListListenerRqst;
    Context mContext;
    ArrayList<Rqst_Helper> mData;


    public AdapterClassRqst(Context mContext, ArrayList<Rqst_Helper> list, OnListListenerRqst onListListenerRqst) {
        this.mData = list;
        this.mContext = mContext;
        this.mOnListListenerRqst = onListListenerRqst;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_request, parent, false);
        final ViewHolder vHolder = new ViewHolder(view, mOnListListenerRqst);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.hospital.setText(""+ mData.get(i).getDonorHaveToGoLocationName());
        holder.bloodGroup.setText(""+ mData.get(i).getBloodGroup());
        holder.condition.setText(""+ mData.get(i).getCondition());
        holder.noOfBags.setText(""+ mData.get(i).getNoOfBags());

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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView hospital, noOfBags, bloodGroup, condition;
        private LinearLayout item_rqst;
        OnListListenerRqst onListListenerRqst;

        public ViewHolder(@NonNull View itemView, OnListListenerRqst onListListenerRqst) {
            super(itemView);

            item_rqst = itemView.findViewById(R.id.Rqst_id);
            hospital = itemView.findViewById(R.id.Rqst_hospital);
            bloodGroup = itemView.findViewById(R.id.Rqst_bld_grp);
            noOfBags = itemView.findViewById(R.id.Rqst_no_of_bags);
            condition = itemView.findViewById(R.id.Rqst_condition);
            this.onListListenerRqst = onListListenerRqst;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            view.setBackgroundColor(Color.RED);
            onListListenerRqst.OnListClickRqst(getAdapterPosition());
        }
    }

    public interface OnListListenerRqst{
        void OnListClickRqst(int position);
    }
}