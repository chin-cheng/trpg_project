package com.example.user.trpg_project_ver01;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.member) {
            // Handle the camera action
            Toast toast = Toast.makeText(this,
                    "Hell!", Toast.LENGTH_LONG);
            //顯示Toast
            toast.show();
            Intent intent = new Intent();
            //將原本Activity的換成MainActivity
            intent.setClass(this, MemberActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.play) {
            Intent intent = new Intent();
            //將原本Activity的換成MainActivity
            intent.setClass(this, ShowStoryListActivity.class);
            startActivity(intent);
        }else if (id == R.id.exit) {

            finish();

        }else if (id == R.id.about) {
            new AlertDialog.Builder(this).setTitle("對話視窗")//設定視窗標題
                    .setIcon(R.mipmap.ic_launcher)//設定對話視窗圖示
                    .setMessage(R.string.about)//設定顯示的文字
                    .setPositiveButton("yes",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();}}).show();

        }else if (id == R.id.create) {
            Intent intent = new Intent();
            intent.setClass(this, CreateGameActivity.class);
            startActivity(intent);

        } else if (id == R.id.edit) {
            Intent intent = new Intent();
            intent.setClass(this, ShowStoryListActivity.class);
            startActivity(intent);
/*
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
