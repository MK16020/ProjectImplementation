package com.example.e_numberapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CameraOutcomes extends Fragment implements EnumAdapter.SelectedEnum {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<EnumModel> enumModelList = new ArrayList<>();
    List<EnumModel> modifiedList = new ArrayList<>();
    EnumAdapter enumAdapter;
    Context context;
    TextView textView;
    String[] cameraResult;
    String text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_camera_outcomes, container, false);


        Intent intent = getActivity().getIntent();
        cameraResult = intent.getStringArrayExtra("cam1");


        recyclerView = v.findViewById(R.id.recview22);//where the information will be

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);//prepare recycler to take info
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));// how they are displayed

        databaseReference = FirebaseDatabase.getInstance().getReference("enumbers");//database reference
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {//take the info from database to the arraylist
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds:snapshot.getChildren()){
                    EnumModel data = ds.getValue(EnumModel.class);
                    enumModelList.add(data);
                }
                filterData(enumModelList, cameraResult);//link the arraylist with the adapter.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        return v;

    }

    public void filterData(List<EnumModel> enumModelList, String[] cameraResult){
        for (EnumModel enumlist : enumModelList) {
            for (int i=0; i<cameraResult.length; i++){
                if (enumlist.getE_no().equals(cameraResult[i])) {
                    Log.d("zainab", "we are in getE_no "+cameraResult[i]);
                    modifiedList.add(enumlist);
                }

            }

        }
        adapt(modifiedList);
    }

    public void adapt(List<EnumModel> enumModelList){//link db with adapter

        enumAdapter = new EnumAdapter(enumModelList,this);
        recyclerView.setAdapter(enumAdapter);
    }
    @Override
    public void selectedEnum(EnumModel enumModel) {
        startActivity(new Intent(getActivity(), SelectedEnumActivity.class).putExtra("data",enumModel));

    }
}