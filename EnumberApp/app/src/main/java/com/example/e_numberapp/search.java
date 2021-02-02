package com.example.e_numberapp;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class search extends Fragment {

    DatabaseReference reference;
    ListView  mylistview;
    ArrayList<String> myarraylist;
    ArrayAdapter<String> myarrayadapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

        ArrayList<String> myarraylist = new ArrayList<>();

        ListView  mylistview= (ListView) findViewById(R.id.listView);



        mylistview=(ListView) findViewById(R.id.listView);
        myarraylist = new ArrayList<>();
        myarrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myarraylist);
        mylistview.setAdapter(myarrayadapter);


        ArrayList<String> myarraylist = new ArrayList<>();

        ArrayAdapter<String> myarrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myarraylist);




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener);







        reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
