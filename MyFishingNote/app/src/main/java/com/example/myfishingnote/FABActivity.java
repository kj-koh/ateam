package com.example.myfishingnote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myfishingnote.databinding.ActivityFabBinding;

public class FABActivity extends AppCompatActivity {

    ActivityFabBinding bi;
    boolean isRotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bi = DataBindingUtil.setContentView(this, R.layout.activity_fab);

        bi.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = ViewAnimation.rotateFab(v, isRotate);
            }
        });

        ViewAnimation.init(bi.fabCamera);
        ViewAnimation.init(bi.fabAddNote);

        //fab
        bi.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = ViewAnimation.rotateFab(v, !isRotate);
                if(isRotate){
                    ViewAnimation.showIn(bi.fabCamera);
                    ViewAnimation.showIn(bi.fabAddNote);
                }else{
                    ViewAnimation.showOut(bi.fabCamera);
                    ViewAnimation.showOut(bi.fabAddNote);
                }

            }
        });

        //미니fab
        bi.fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FABActivity.this, "Camera", Toast.LENGTH_SHORT).show();
            }
        });

        bi.fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FABActivity.this, "AddNote", Toast.LENGTH_SHORT).show();
            }
        });
        //setContentView(R.layout.activity_fab);
    }
}