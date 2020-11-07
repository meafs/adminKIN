package com.sust.adminkinblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> implements Filterable {

    Context mContext;
   private ArrayList<Dnr_Healper> mData;
   private ArrayList<Dnr_Healper> mDataFull;

   private OnListListener mOnListListener;

    public AdapterClass(Context mContext, ArrayList<Dnr_Healper> list, OnListListener onListListener) {
        this.mData = list;
        this.mContext = mContext;
        this.mOnListListener = onListListener;
        mDataFull = new ArrayList<>(list);
    }

//    public AdapterClass(ArrayList<Dnr_Healper> list) {
//
//      this.mData = list;
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_donor, parent, false);
        final ViewHolder vHolder = new ViewHolder(view, mOnListListener);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.dnr_Name.setText(mData.get(i).getFullName());
        holder.bld_grp.setText(mData.get(i).getBloodGroup());
        holder.phn_num.setText(mData.get(i).getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView dnr_Name,bld_grp,phn_num;
        private LinearLayout item_Donor;
        OnListListener onListListener;

        public ViewHolder(@NonNull View itemView, OnListListener onListListener) {
            super(itemView);

            this.onListListener = onListListener;

            item_Donor= itemView.findViewById(R.id.Dnr_id);;
            dnr_Name = itemView.findViewById(R.id.dnr_name);
            bld_grp = itemView.findViewById(R.id.bld_grp);
            phn_num= itemView.findViewById(R.id.phn_num);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onListListener.OnListClick(getAdapterPosition());
        }
    }

    @Override
    public Filter getFilter() {

        return fileterd;
    }
    private Filter fileterd = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Dnr_Healper> fileterdList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                fileterdList.addAll(mDataFull);
            }
            else{
                String searchKey = constraint.toString().toLowerCase().trim();
                for(Dnr_Healper item: mDataFull){
                    if(item.getBloodGroup().toLowerCase().contains(searchKey)){

                        fileterdList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = fileterdList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
              mData.clear();
              mData.addAll((Collection<? extends Dnr_Healper>) results.values);
              notifyDataSetChanged();
        }
    };

    public interface OnListListener{
        void OnListClick(int position);
    }
}
