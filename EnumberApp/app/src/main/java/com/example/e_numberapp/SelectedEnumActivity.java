package com.example.e_numberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectedEnumActivity extends AppCompatActivity {

    TextView tvname,tvtype,tvstatus,tvsideeff;

    Button  colorbt,sharebtn,historybt;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("history");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_enum);

        Intent intent = getIntent();//open this activity over the others
        EnumModel enumModel = (EnumModel) intent.getSerializableExtra("data");

        tvname = findViewById(R.id.selectedname);
        tvtype = findViewById(R.id.selecteddetails);
        tvstatus = findViewById(R.id.selectedstatus);
        tvsideeff = findViewById(R.id.selectedsideeff);

        colorbt = findViewById(R.id.color);
        colorbt.setOnClickListener(new View.OnClickListener() { //set the color to know the status

            @Override
            public void onClick(View v) {

              switch (enumModel.getColor_Id()){
                  case 1:
                      colorbt.setText(enumModel.getE_no().toString());
                      colorbt.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.green));
                      break;

                  case 2:
                      colorbt.setText(enumModel.getE_no().toString());
                      colorbt.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.orange));
                      break;

                  case 3:
                      colorbt.setText(enumModel.getE_no().toString());
                      colorbt.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.red));
                      break;

              }
            }
        });



        if (intent.getExtras() != null){

            //take information from model with Serializable
            tvname.setText(enumModel.getName());
            tvtype.setText(enumModel.getDetail());
            tvstatus.setText(enumModel.getStatus());
            tvsideeff.setText(enumModel.getSide_effect());
        }
        String e = enumModel.getE_no();
        String name =tvname.getText().toString();
        String type =tvtype.getText().toString();
        String status =tvstatus.getText().toString();
        String sideeff =tvsideeff.getText().toString();

        historybt=findViewById(R.id.historybt);
        historybt.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                HashMap<String , String> historyenum = new HashMap<>();

                historyenum.put("E_no" , e);
                historyenum.put("Name" , name);
                historyenum.put("Type" , type);
                historyenum.put("Status" , status);
                historyenum.put("Side_effect" , sideeff);

                root.push().setValue(historyenum);
            }
        });
        sharebtn=findViewById(R.id.sharebtn);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create a new Intent object
                Intent info = new Intent();
                // Set the action to send
                info.setAction(Intent.ACTION_SEND);

              /*  info.putExtra(Intent.EXTRA_TEXT,e);
                info.putExtra(Intent.EXTRA_TEXT,name);
                info.putExtra(Intent.EXTRA_TEXT,type);
                info.putExtra(Intent.EXTRA_TEXT,status);
                info.putExtra(Intent.EXTRA_TEXT,sideeff);*/

                String text = e+"\n";
                text += name+"\n";
                text += type+"\n";
                text += status+"\n";
                text += sideeff+"\n";

                info.putExtra(Intent.EXTRA_TEXT,text);
                // Set type
                info.setType("text/plain");
                //Start the activity with the chooser intent
                startActivity(Intent.createChooser(info,getResources().getText(R.string.app_name)));

            }
        });

    }

}