package com.collegefavourite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentFavourite extends Fragment {
    RecyclerView my_recycler_view;
    DBHelper dbHelper;
    Model_List model_list;
    Controller controller;
    RecyclerView.LayoutManager mLayoutManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        my_recycler_view = view.findViewById(R.id.my_recycler_view);
        dbHelper = new DBHelper(view.getContext());
        controller = new Controller();
        ArrayList<String> c = dbHelper.getAllFavouriteCotacts();
        for(int i=0;i<c.size();i++){
            String s =c.get(i);
            String[] a;
            a = s.split(" ");
            model_list = new Model_List(a[0],a[1]);
            controller.setMatches(model_list);
            Log.e("Data",c.get(i));
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLayoutManager = new LinearLayoutManager(view.getContext());
                my_recycler_view.setLayoutManager(mLayoutManager);
                CustomAdapterList a = new CustomAdapterList(view.getContext(), controller.getMyMatches(),1);
                my_recycler_view.setAdapter(a);
            }
        });
        return view;
    }
}
