package com.example.myfishingnote;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

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
                Snackbar.make(view, "Create a new note", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

/*
        // 화면에 붙여줄 프래그먼트 선언 및
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_tide, R.id.nav_suggest, R.id.nav_note, R.id.nav_map)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
*/


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //drawer를 닫아준다
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout) ;
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT) ;
                }

                switch (item.getItemId()) {
                    case R.id.nav_home :

                        break;
                    case R.id.nav_tide :

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        TideFragment fragment_tide = new TideFragment();

                        transaction.replace(R.id.nav_host_fragment, fragment_tide);
                        transaction.commit();
                        break;

                    case R.id.nav_suggest :

                        break;

                    case R.id.nav_note :

                        break;

                    case R.id.nav_map :

                        break;



                    case R.id.nav_settings :
                        Toast.makeText(MainActivity.this, "settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_help :
                        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        break;
                    default :
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



}