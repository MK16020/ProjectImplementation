package com.example.e_numberapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.Objects;


public class camera extends Fragment {

    private ImageView imageView;
    private Button button;
    TextView t1;


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_camera, container, false);
        imageView = (ImageView) v.findViewById(R.id.capturedImage);
        button = (Button) v.findViewById(R.id.openCamera);
        t1 = (TextView) v.findViewById(R.id.textView2);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent open_Camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_Camera, 100);

            }
        });
        return v;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(photo);


    }

    public void getTextFromImage (View v){
        Bitmap bitmap = BitmapFactory.decodeResource(Objects.requireNonNull(getActivity()).getApplicationContext().getResources(),R.drawable.p);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getActivity().getApplicationContext()).build();

        if(!textRecognizer.isOperational()){
            Toast.makeText(getActivity().getApplicationContext(), "Could not get the text",Toast.LENGTH_SHORT ).show();
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items= textRecognizer.detect(frame);

            StringBuilder sb= new StringBuilder();

            for(int i=0; i<items.size(); ++i){
                TextBlock myitem = items.valueAt(i);
                sb.append(myitem.getValue());
                sb.append("\n");
            }
            t1.setText(sb.toString());
        }

    }

}





