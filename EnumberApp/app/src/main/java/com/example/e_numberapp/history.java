package com.example.e_numberapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class history extends Fragment implements EnumAdapter.SelectedEnum{

    Toolbar toolbar;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<EnumModel> enumModelList = new ArrayList<>();
    EnumAdapter enumAdapter;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_history, container, false);

        toolbar = v.findViewById(R.id.toolbar);
        recyclerView = v.findViewById(R.id.recyclerview);//where the information will be

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);//prepare recycler to take info
        context = container.getContext();
        recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));// how they are displayed

        databaseReference = FirebaseDatabase.getInstance().getReference("history");//database reference
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {//take the info from database to the arraylist
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds:snapshot.getChildren()){
                    EnumModel data = ds.getValue(EnumModel.class);
                    enumModelList.add(data);
                }
                adapt(enumModelList);//link the arraylist with the adapter.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;
    }

    public void adapt(List<EnumModel> enumModelList){//link db with adapter
        enumAdapter = new EnumAdapter(enumModelList,this);
        recyclerView.setAdapter(enumAdapter);
    }

    @Override
    public void selectedEnum(EnumModel enumModel) {//identify the selected row
        startActivity(new Intent(getActivity(), SelectedEnumActivity.class).putExtra("data",enumModel));
    }
}

