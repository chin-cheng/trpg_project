package com.example.user.trpg_project_ver01;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    /*
     * 歡迎畫面_WelcomeActivity.java
     * 主介面(選擇遊玩或創建)_MainActivity.java
     * 登入介面_LoginActivity.java
     * 註冊介面_RegisterActivity.java
     * 會員專區_MemberActivity.java
     * 創建遊戲頁面_CreateGameActivity.java
     * 遊玩介面_PlayActivity.java
     * 個人資料頁面_ProfileActivity.java
     * by CHANG,CHIN-CHENG
     * */

    Button testbtn;
    TextView username;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("首頁");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//寫入測試
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message/sc");
/*
        myRef.setValue("Hello, World!");*/
//---
        //讀取測試

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("test", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("test2", "Failed to read value.", error.toException());
            }
        });

        //-----

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        username=(TextView) findViewById(R.id.na);
       // username.setText(user.getUid());







        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //配色
        /*
        Menu menu = navigationView.getMenu();

        MenuItem member= menu.findItem(R.id.member);
        SpannableString s = new SpannableString(member.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        member.setTitle(s);


*/
        navigationView.setNavigationItemSelectedListener(this);


/*
        testbtn=findViewById(R.id.testbtn);
        //崩潰測試
        testbtn.setText("Crash!");
        testbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });
        addContentView(testbtn,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
//崩潰測試結束
*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.member) {
            // Handle the camera action
            Toast toast = Toast.makeText(MainActivity.this,
                    "Hell!", Toast.LENGTH_LONG);
            //顯示Toast
            toast.show();
            Intent intent = new Intent();
            //將原本Activity的換成MainActivity
            intent.setClass(MainActivity.this, MemberActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.play) {
            Intent intent = new Intent();
            //將原本Activity的換成MainActivity
            intent.setClass(MainActivity.this, PlayVer2Activity.class);
            startActivity(intent);
        }else if (id == R.id.exit) {

            finish();

        }else if (id == R.id.about) {
            new AlertDialog.Builder(MainActivity.this).setTitle("對話視窗")//設定視窗標題
            .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
.setMessage(R.string.about)//設定顯示的文字
.setPositiveButton("yes",new DialogInterface.OnClickListener(){
@Override
public void onClick(DialogInterface dialog, int which) {
finish();}}).show();

        }else if (id == R.id.create) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, CreateGameActivity.class);
            startActivity(intent);

//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
*/

}
