package com.example.e_numberapp;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/*import com.google.firebase.*;
import java.util.ArrayList;*/

public class search extends Fragment {


    ListView mylistview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);

        /*ArrayList<String> myarraylist = new ArrayList<>();
        ArrayAdapter<String> myarrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,myarraylist);
        mylistview =(ListView) findViewById(R.id.listView);
        mylistview.setAdapter(myarrayadapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener);*/
    }


}
