package com.example.myfishingnote;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myfishingnote.ui.help.HelpActivity;
import com.example.myfishingnote.ui.home.HomeFragment;
import com.example.myfishingnote.ui.map.MapsFragment;
import com.example.myfishingnote.ui.member.ui.login.LoginActivity;
import com.example.myfishingnote.ui.note.AddNote;
import com.example.myfishingnote.ui.note.NoteFragment;
import com.example.myfishingnote.ui.settings.SettingsActivity;
import com.example.myfishingnote.ui.suggest.SuggestFragment;
import com.example.myfishingnote.ui.tide.TideFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //화면 상단 main.xml

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
    private FloatingActionButton fabAdd, fabAddNote, fabCamera, fabMap;
    private boolean isFabOpen = false;

    private File file;

    //뒤로가기 버튼 선언
    private BackPressCloseHandler backPressCloseHandler;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //초기에 세팅된 splash테마를 onCreate시에 일반테마로 변경
        //intro처리
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);

        //activity_main_xml화면을 붙여준다
        /**
         * 뷰 객체를 미리 담아서 같은 뷰를 참조하게 만듦
         */
        View rootView = getLayoutInflater().from(this).inflate(R.layout.activity_main, null);
        setContentView(rootView);

        //앱종료를 위한 back버튼 선언
        /**
         * 뷰를 생성자로 할당
         */
        backPressCloseHandler = new BackPressCloseHandler(this, rootView);

        //툴바 선언
        Toolbar toolbar = findViewById(R.id.toolbar);


        //액션바 선언
        setSupportActionBar(toolbar);

        //Drawer붙여주기
        drawerLayout = findViewById(R.id.drawer_layout);

        /* 액션바에 Drawer Toggle 버튼 추가하기 */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /* 프래그먼트 설정을 메소드로 뺐습니다.. (보기 편하게) */
        fragmentSettings();

        /* fab 관련 */
        fabAdd = findViewById(R.id.fabAdd);
        fabAddNote = findViewById(R.id.fabAddNote);
        fabCamera = findViewById(R.id.fabCamera);
        fabMap = findViewById(R.id.fabMap);

        fabAddNote.hide();
        fabCamera.hide();
        fabMap.hide();

        //focus변경시 fab버튼 원상복귀
        fabAdd.setFocusable(true);
        fabAdd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus != true) {
                        fabAdd.animate()
                                .rotationBy(135)
                                .setDuration(100)
                                .start();
                        fabAddNote.hide();
                        fabCamera.hide();
                        fabMap.hide();
                        isFabOpen = false;

                }
            }
        });

        //FAB 클릭시 처리
        fabAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                toggleFab(view);
            }
        });

        fabAddNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Intent intent = new Intent(MainActivity.this, AddNote.class);
                startActivity(intent);

                Snackbar.make(view, "!!!!", Snackbar.LENGTH_SHORT).show();
            }
        });

        fabCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        fabMap.setOnClickListener(new View.OnClickListener() {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            @Override
            public void onClick(View view) {
                transaction.replace(R.id.nav_host_fragment, mapsFragment).commit();

            }
        });

        //navHeader를 클릭하면 로그인 페이지를 띄운다
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        View header = navView.getHeaderView(0);
        LinearLayout navForm = header.findViewById(R.id.nav_form);
        navForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }//onCreate()

    /**
     * fab 토글 액션 show/hide
     *
     */
    /*private void toggleFab() {
    /* fab 토글 액션 */
    private void toggleFab() {
        if (isFabOpen) {
            fabAdd.animate()
                    .rotationBy(225)
                    .setDuration(300)
                    .start();
            fabAddNote.hide();
            fabCamera.hide();
            fabMap.hide();
            isFabOpen = false;
        } else {
            fabAdd.animate()
                    .rotationBy(225)
                    .setDuration(300)
                    .start();
            fabAddNote.show();
            fabCamera.show();
            fabMap.show();
            isFabOpen = true;
        }
    }

    /** 토글액션2 상하fade
    *
    */
    private void toggleFab(View view) {
        if (isFabOpen) {
           ViewAnimation.rotateFab(view, false);
           ViewAnimation.showOut(fabAddNote);
           ViewAnimation.showOut(fabCamera);
           ViewAnimation.showOut(fabMap);
            isFabOpen = false;
        } else {
            ViewAnimation.rotateFab(view, true);
            ViewAnimation.showIn(fabAddNote);
            ViewAnimation.showIn(fabCamera);
            ViewAnimation.showIn(fabMap);
            isFabOpen = true;
        }
    }

    /* 프래그먼트 설정 메소드 */
    private void fragmentSettings() {

        /* 프래그먼트 객체 생성 */
        homeFragment = new HomeFragment();
        tideFragment = new TideFragment();
        suggestFragment = new SuggestFragment();
        noteFragment = new NoteFragment();
        mapsFragment = new MapsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, homeFragment).commit();

        //NavigationDrawer 메뉴설정
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

                        intent = new Intent(MainActivity.this, HelpActivity.class);
                        startActivity(intent);
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
            CoordinatorLayout fabMain = findViewById(R.id.fabMain);
            backPressCloseHandler.onBackPressed(fabMain);
        }
    }

    public void takePicture() {
        if (file == null) {
            file = createFile();
        }//if

        Uri fileUri = FileProvider.getUriForFile(this, "com.example.myfishingnote.fileprovider", file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent,101);
        }//if

    }//takePicture

    private File createFile() {
        String filename = "capture.jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(filename);
        File outFile = new File(storageDir, filename);
        return outFile;
    }

}