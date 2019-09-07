package com.collegefavourite;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by ahmedgagan on 20/12/18.
 */



public class CustomAdapterList extends RecyclerView.Adapter<CustomAdapterList.CustomViewHolder> {
    private ArrayList<Model_List> list = new ArrayList<>();
    private Context mContext;
    DBHelper dbHelper ;
    int page;

    public CustomAdapterList(Context context, ArrayList<Model_List> restaurants,int i) {
        mContext = context;
        list = restaurants;
        this.page = i;
    }
    @Override
    public CustomAdapterList.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapterList.CustomViewHolder holder, int position) {
        holder.bindRestaurant(list.get(position));
    }

    @Override
    public int getItemCount() {
        Log.e("size",""+ list.size());
        return list.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ToggleButton toggleButton;
        CardView card_viewatRecent;
        public CustomViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            name = itemView.findViewById(R.id.name);
            card_viewatRecent = itemView.findViewById(R.id.card_viewatRecent);
            toggleButton = itemView.findViewById(R.id.button_favorite);
            dbHelper = new DBHelper(itemView.getContext());
        }

        public void bindRestaurant(final Model_List listData) {
            name.setText(listData.getName());
            Log.e("Fav",listData.getFavourite());
            if(listData.getFavourite().equals("FAVORITE")){
                Log.e("Yaha","Okay");
                toggleButton.setChecked(true);
            }
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        dbHelper.updateContact(listData.getName(),"FAVORITE");
                    }
                    else {
                        dbHelper.updateContact(listData.getName(),"NOTFAVORITE");
                        Log.e("Str",itemView.getContext().toString());
                        if(page==1){
                            CustomAdapterList.this.list.remove(listData);
                            notifyDataSetChanged();
                        }
                    }
                }
            });
        }
    }
}

