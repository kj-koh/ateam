package com.example.myfishingnote;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

public class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;
    private Snackbar snackbar;
    private Activity activity;
    private View view;


    public BackPressCloseHandler(Activity context, View view) {
        this.activity = context;
        this.view = view;
    }

    public void onBackPressed(CoordinatorLayout fabMain) {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide(fabMain);
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            snackbar.dismiss();
            // toast.cancel();
        }
    }

    public void showGuide(CoordinatorLayout fabMain) {

        toast = Toast.makeText(activity, R.string.back_twice, Toast.LENGTH_SHORT);
        toast.show();


        //스낵바 처리
        //Snackbar.make(fabMain, "Replace with your own action", Snackbar.LENGTH_SHORT)
          //      .setAction("Action", null).show();


       // snackbar = Snackbar.make(view, "!!!!!", Snackbar.LENGTH_SHORT);
       // snackbar.show();
        /*toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();*/
    }
}