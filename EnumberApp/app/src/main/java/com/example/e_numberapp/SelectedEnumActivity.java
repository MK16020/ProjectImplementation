package com.example.e_numberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class SelectedEnumActivity extends AppCompatActivity {


    TextView tvname;
    TextView tvtype;
    TextView tvstatus;
    Button  colorbt;

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
                      colorbt.setText(enumModel.E_no.toString());
                      colorbt.setBackgroundColor(Color.GREEN);
                      break;
                  case 2:
                      colorbt.setText(enumModel.E_no.toString());
                      colorbt.setBackgroundColor(Color.YELLOW);
                      break;
                  case 3:
                      colorbt.setText(enumModel.E_no.toString());
                      colorbt.setBackgroundColor(Color.RED);
                      break;
              }


            }
        });

        tvname = findViewById(R.id.selectedname);
        tvtype = findViewById(R.id.selectedtype);
        tvstatus = findViewById(R.id.selectedstatus);

        if (intent.getExtras() != null){

            //take information from model with Serializable
            tvname.setText(enumModel.getName());
            tvtype.setText(enumModel.getDetail());
            tvstatus.setText(enumModel.getStatus());
        }
    }

}