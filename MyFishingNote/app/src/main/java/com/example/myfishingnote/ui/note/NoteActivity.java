package com.example.myfishingnote.ui.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myfishingnote.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class NoteActivity extends AppCompatActivity implements  OnTabItemSelectedListener{

    NoteListFragment noteListFragment;      //노트 리스트 프래그먼트
    NoteAddFragment noteAddFragment;        //노트 추가 프래그먼트
    NoteChartFragment noteChartFragment;    //노트 통계 프래그먼트


    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteListFragment = new NoteListFragment();
        noteAddFragment = new NoteAddFragment();
        noteChartFragment = new NoteChartFragment();//통계 프래그먼트 추가할것

        getSupportFragmentManager().beginTransaction().replace(R.id.note_container, noteListFragment).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //return false;
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "첫번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.note_container, noteListFragment).commit();
                        return  true;

                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "두번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.note_container, noteAddFragment).commit();
                        return  true;

                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "세번째 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.note_container, noteChartFragment).commit();//추후 변경
                        return  true;

                }//switch

            return false;


            }
        });




    }//onCreate()


    @Override
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
    }
}//class