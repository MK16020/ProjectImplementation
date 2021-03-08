package com.example.e_numberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedEnumActivity extends AppCompatActivity {


    TextView tvname;
    TextView tvtype;
    TextView tvstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_enum);


        tvname = findViewById(R.id.selectedname);
        tvtype = findViewById(R.id.selectedtype);
        tvstatus = findViewById(R.id.selectedstatus);

        Intent intent = getIntent();//open this activity over the others
        if (intent.getExtras() != null){
            EnumModel enumModel = (EnumModel) intent.getSerializableExtra("data");
            //take information from model with Serializable
            tvname.setText(enumModel.getName());
            /*tvtype.setText(enumModel.);*/
            tvstatus.setText(enumModel.getStatus());
        }
    }
}