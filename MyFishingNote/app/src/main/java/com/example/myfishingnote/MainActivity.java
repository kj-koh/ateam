package com.example.myfishingnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.myfishingnote.databinding.ActivityFabBinding;
import com.example.myfishingnote.ui.home.HomeFragment;
import com.example.myfishingnote.ui.map.MapFragment;
import com.example.myfishingnote.ui.map.MapsFragment;
import com.example.myfishingnote.ui.note.AddNote;
import com.example.myfishingnote.ui.note.NoteFragment;
import com.example.myfishingnote.ui.settings.SettingsActivity;
import com.example.myfishingnote.ui.suggest.SuggestFragment;
import com.example.myfishingnote.ui.tide.TideFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    //화면 상단 main.xml

    //drawer선언
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    //AniamtedFAB사용을 위한 바인딩과 애니메이션 처리준비
    ActivityFabBinding bi;
    boolean isRotate = false;

    /**
     * 프래그먼트 선언
     */
    HomeFragment homeFragment;
    TideFragment tideFragment;
    SuggestFragment suggestFragment;
    NoteFragment noteFragment;
    MapsFragment mapsFragment;

    private BackPressCloseHandler backPressCloseHandler;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //초기에 세팅된 splash테마를 onCreate시에 일반테마로 변경
        //intro처리
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);

        //앱종료를 위한 back버튼 선언
        backPressCloseHandler = new BackPressCloseHandler(this);

        //activity_main_xml화면을 붙여준다
        setContentView(R.layout.activity_main);

        //툴바 선언
        Toolbar toolbar = findViewById(R.id.toolbar);

        //액션바 선언
        setSupportActionBar(toolbar);

        //액션 버튼
       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Snackbar.make(view, "Create a new note", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);
            }
        });*/


        //app_bar_main과 바인딩
        bi = DataBindingUtil.setContentView(this, R.layout.app_bar_main);
        //fabAdd에 클릭리스너 붙여준다
      /*  bi.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRotate = ViewAnimation.rotateFab(v, isRotate);
            }
        });*/

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

        //미니FAB의 클릭 리스너 2개
        bi.fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Camera", Toast.LENGTH_SHORT).show();
            }
        });

        bi.fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "AddNote", Toast.LENGTH_SHORT).show();
            }
        });

        //Drawer붙여주기
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Drawer 토글 버튼 활성화
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /**
         * 프래그먼트 객체생성
         */
        homeFragment = new HomeFragment();
        tideFragment = new TideFragment();
        suggestFragment = new SuggestFragment();
        noteFragment = new NoteFragment();
        mapsFragment = new MapsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                //drawer를 닫아준다
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT) ;
                }

                switch (item.getItemId()) {
                    case R.id.nav_home :
                        transaction.replace(R.id.nav_host_fragment, homeFragment).commit();
                        break;
                    case R.id.nav_tide :
                        transaction.replace(R.id.nav_host_fragment, tideFragment).commit();
                        break;
                    case R.id.nav_suggest :
                        transaction.replace(R.id.nav_host_fragment, suggestFragment).commit();
                        break;
                    case R.id.nav_note :
                        transaction.replace(R.id.nav_host_fragment, noteFragment).commit();
                        break;
                    case R.id.nav_map :
                        transaction.replace(R.id.nav_host_fragment, mapsFragment).commit();
                        break;
                    case R.id.nav_settings :
                        // Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_help :
                        // Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        // Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                        // startActivity(intent);
                        break;
                }

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //메인화면에서 Back Button처리
    @Override
    public void onBackPressed() {
        //Drawer가 열려있으면 Drawer를 닫아준다
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            //Home이 아니면 Home으로 변경해준다.
        } else if (getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment) != homeFragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();
        //Home에서는 BackPressCloseHandler의 onBackPressed()로 연결해준다.
            //onBackPressed : 클릭을 2번해야 나갈 수 있게 한다
        } else {
            //super.onBackPressed();
            backPressCloseHandler.onBackPressed();
        }
    }
}