package com.collegefavourite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentHome extends FragmentList {
    EditText ETname;
    Button ButtonAddName;
    DBHelper dbHelper;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        ETname = view.findViewById(R.id.ETname);
        ButtonAddName = view.findViewById(R.id.ButtonAddName);
        dbHelper = new DBHelper(view.getContext());
        ButtonAddName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ETname.getText().toString().equals("")){
                    ETname.setError("Enter Unique Name");
                }
                else {

                    long b = dbHelper.insertContact(ETname.getText().toString(), "NOTFAVORITE");
                    Log.e("Ghusa",String.valueOf(b));
                    if(b>0){
                        ETname.setText("");
                        Toast.makeText(getActivity().getApplicationContext(), "Name Added", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getActivity().getApplicationContext(), "Name Already Exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
}
