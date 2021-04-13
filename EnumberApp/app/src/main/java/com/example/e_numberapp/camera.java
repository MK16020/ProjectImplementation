package com.example.e_numberapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

import com.google.android.gms.tasks.OnFailureListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import static android.app.Activity.RESULT_OK;


public class camera extends Fragment implements Serializable {

    private Button captureImageBtn, detectTextBtn, searchTextBtn;
    private ImageView imageView;
    private TextView textView;
    Bitmap imageBitmap;
    String [] result = new String[30];
    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_camera, container, false);

        captureImageBtn = (Button) v.findViewById(R.id.capture_image_btn);
        detectTextBtn = (Button) v.findViewById(R.id.detect_text_image_btn);
        searchTextBtn = (Button) v.findViewById(R.id.search_text_image_btn);
        imageView = (ImageView) v.findViewById(R.id.image_view);
        textView =(TextView) v.findViewById(R.id.text_display);


        captureImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();
                textView.setText("");
            }
        });

        detectTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detectTextFromImage ();
            }
        });

        searchTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( textView.getText().toString()!=null){
                    searchTextFromImage( textView.getText().toString());
                }
                else {
                    Toast.makeText(getActivity(), "Error: there is no text found" , Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void detectTextFromImage()
    {
        FirebaseVisionImage firrebaseVisionImage = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextDetector firebaseVisionTextDetector = FirebaseVision.getInstance().getVisionTextDetector();
        firebaseVisionTextDetector.detectInImage(firrebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                displayTextFromImage(firebaseVisionText);//put it in the new page.y
                //open new page here.

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error:" , e.getMessage());
            }
        });
    }

    private void displayTextFromImage(FirebaseVisionText firebaseVisionText)
    {
        String text=null;

        List<FirebaseVisionText.Block> blockList = firebaseVisionText.getBlocks();
        if(blockList.size() == 0 ){
            Toast.makeText(getActivity(), "No text foung in image " , Toast.LENGTH_LONG).show();

        }
        else {
            for(FirebaseVisionText.Block block: firebaseVisionText.getBlocks()){

                text = block.getText();
                textView.setText(text);// no need for this.
            }

        }
    }
    private void searchTextFromImage(String text){
        ArrayList<String> cameraText = new ArrayList<String>();


        int j=0;


        for (int i=0; i<text.length()-4;i++){

            if(text.charAt(i) == 'E' && Character.isDigit(text.charAt(i+1))
                    && Character.isDigit(text.charAt(i+2))
                    &&Character.isDigit(text.charAt(i+3))){

                result[j]=text.substring(i,i+4);
                Log.d("zoooz", "we are in text "+result[j] );
               j++;

            }
        }

        if (result[0]!= null){
            startActivity(new Intent(getActivity(), Result.class).putExtra("cam1", result));

        }

        else{
            Toast.makeText(getActivity(), "No E-numbers found " , Toast.LENGTH_LONG).show();
        }










    }

}










