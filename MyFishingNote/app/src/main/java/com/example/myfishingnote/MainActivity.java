package com.example.myfishingnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

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
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Create a new note", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);


            }
        });

        //Drawer붙여주기
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Drawer 토글 버튼 활성화
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}