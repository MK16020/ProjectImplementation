package com.example.e_numberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedEnumActivity extends AppCompatActivity {

    TextView tvenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_enum);

        tvenum = findViewById(R.id.selectedenum);

        Intent intent = getIntent();//open this activity over the others
        if (intent.getExtras() != null){
            EnumModel enumModel = (EnumModel) intent.getSerializableExtra("data");
            //take information from model with Serializable
            tvenum.setText(enumModel.getName());
        }
    }
}