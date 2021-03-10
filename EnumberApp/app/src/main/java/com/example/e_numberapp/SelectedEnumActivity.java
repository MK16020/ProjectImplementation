package com.example.e_numberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.graphics.Color;

public class SelectedEnumActivity extends AppCompatActivity {


    Button  colorbt;

    TextView tvname;
    TextView tvtype;
    TextView tvstatus;
    TextView tvsideeff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_enum);

        Intent intent = getIntent();//open this activity over the others
        EnumModel enumModel = (EnumModel) intent.getSerializableExtra("data");


        colorbt = findViewById(R.id.color);
        colorbt.setOnClickListener(new View.OnClickListener() { //set the color to know the status

            @Override
            public void onClick(View v) {

              switch (enumModel.getColor_Id()){
                  case 1:
                      colorbt.setText(enumModel.getE_no().toString());
                      colorbt.setBackgroundColor(getResources().getColor(R.color.green));
                      break;
                  case 2:
                      colorbt.setText(enumModel.getE_no().toString());
                      colorbt.setBackgroundColor(getResources().getColor(R.color.yellow));
                      break;
                  case 3:
                      colorbt.setText(enumModel.getE_no().toString());
                      colorbt.setBackgroundColor(getResources().getColor(R.color.red));
                      break;
              }


            }
        });

        tvname = findViewById(R.id.selectedname);
        tvtype = findViewById(R.id.selecteddetails);
        tvstatus = findViewById(R.id.selectedstatus);
        tvsideeff = findViewById(R.id.selectedsideeff);

        if (intent.getExtras() != null){

            //take information from model with Serializable
            tvname.setText(enumModel.getName());
            tvtype.setText(enumModel.getDetail());
            tvstatus.setText(enumModel.getStatus());
            tvsideeff.setText(enumModel.getSide_effect());
        }
    }

}