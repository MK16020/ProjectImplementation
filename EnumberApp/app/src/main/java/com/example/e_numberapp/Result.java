package com.example.e_numberapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class Result extends AppCompatActivity implements EnumAdapter.SelectedEnum{

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    List<EnumModel> enumModelList = new ArrayList<>();
    List<EnumModel> modifiedList = new ArrayList<>();
    EnumAdapter enumAdapter;
    Context context;
    String[] cameraResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //open this activity over the others
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.d("zainab", "it is not null "+ extras.getStringArray("cam1"));
            cameraResult = }



        recyclerView = findViewById(R.id.recview2);//where the information will be

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);//prepare recycler to take info
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));// how they are displayed

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
    public void selectedEnum(EnumModel enumModel) {//identify the selected row
        startActivity(new Intent(this, SelectedEnumActivity.class).putExtra("data",enumModel));
    }

}
