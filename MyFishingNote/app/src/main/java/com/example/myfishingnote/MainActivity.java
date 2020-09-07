package com.example.myfishingnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.myfishingnote.ui.home.HomeFragment;
import com.example.myfishingnote.ui.map.MapsFragment;
import com.example.myfishingnote.ui.note.NoteFragment;
import com.example.myfishingnote.ui.settings.SettingsActivity;
import com.example.myfishingnote.ui.suggest.SuggestFragment;
import com.example.myfishingnote.ui.tide.TideFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
<<<<<<< Updated upstream
import androidx.fragment.app.Fragment;
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    //drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    /**
     * 프래그먼트 선언
     */
    HomeFragment homeFragment;
    TideFragment tideFragment;
    SuggestFragment suggestFragment;
    NoteFragment noteFragment;
    MapsFragment mapsFragment;

=======

    //drawer선언
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    /* 프래그먼트 선언 */
    private HomeFragment homeFragment;
    private TideFragment tideFragment;
    private SuggestFragment suggestFragment;
    private NoteFragment noteFragment;
    private MapsFragment mapsFragment;

    /* fab 선언 */
    private FloatingActionButton fabAdd, fabAddNote, fabCamera;
    private boolean isFabOpen = false;

    private BackPressCloseHandler backPressCloseHandler;
>>>>>>> Stashed changes

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //splash테마 변경
        setTheme(R.style.AppTheme);

        //ID존재 여부 확인

        //DB확인

        super.onCreate(savedInstanceState);

        //activity_main_xml화면을 붙여준다
        setContentView(R.layout.activity_main);

        //툴바 선언
        Toolbar toolbar = findViewById(R.id.toolbar);

        //액션바 선언 및 플로팅 액션버튼
        setSupportActionBar(toolbar);
<<<<<<< Updated upstream
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Create a new note", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);

=======

        //Drawer붙여주기
        drawerLayout = findViewById(R.id.drawer_layout);

        /* 액션바에 Drawer Toggle 버튼 추가하기 */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /* 프래그먼트 설정을 메소드로 뺐습니다.. (보기 편하게) */
        fragmentSettings();

        /**
         * fab 관련
         */
        fabAdd = findViewById(R.id.fabAdd);
        fabAddNote = findViewById(R.id.fabAddNote);
        fabCamera = findViewById(R.id.fabCamera);

        fabAddNote.hide();
        fabCamera.hide();

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
            }
        });

        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
>>>>>>> Stashed changes

            }
        });

    }

    /**
     * fab 토글 액션
     */
    private void toggleFab() {
        if (isFabOpen) {
            fabAdd.animate()
                    .rotationBy(135)
                    .setDuration(700)
                    .start();
            fabAddNote.hide();
            fabCamera.hide();
            isFabOpen = false;
        } else {
            fabAdd.animate()
                    .rotationBy(135)
                    .setDuration(700)
                    .start();
            fabAddNote.show();
            fabCamera.show();
            isFabOpen = true;
        }
    }


    /**
     * 프래그먼트 설정 메소드
     */
    private void fragmentSettings() {

        /* 프래그먼트 객체 생성 */
        homeFragment = new HomeFragment();
        tideFragment = new TideFragment();
        suggestFragment = new SuggestFragment();
        noteFragment = new NoteFragment();
        mapsFragment = new MapsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else if (getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment) != homeFragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();
        } else {
            super.onBackPressed();
        }
    }
}