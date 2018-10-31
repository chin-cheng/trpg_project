package com.example.user.trpg_project_ver01;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.trpg_project_ver01.models.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {
EditText pro_nickname_edit,pro_birthday_edit,pro_phone_edit,pro_realname_edit;
Spinner pro_sex_spinner;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authListener;
    //FirebaseUser user;
    //FirebaseUser user = firebaseAuth.getCurrentUser();
    private String userUID;
    private DatabaseReference mDatabase;
    private String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userUID =  user.getUid();
        pro_nickname_edit=(EditText)findViewById(R.id.pro_nickname_edit);
        pro_phone_edit= (EditText)findViewById(R.id.pro_phone_edit);
        pro_birthday_edit = (EditText)findViewById(R.id.pro_birthday_edit);
        pro_realname_edit=findViewById(R.id.pro_realname_edit);
        pro_sex_spinner=findViewById(R.id.pro_sex_spinner);



        pro_birthday_edit.setInputType(InputType.TYPE_NULL); //不显示系统输入键盘
//生日菜單
        pro_birthday_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {




            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(hasFocus){
                    showDatePickerDialog();
                }
            }
        });

        pro_birthday_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePickerDialog();
            }
        });

        authListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(
                    @NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null) {
                    Log.d("onAuthStateChanged", "登入:"+
                            user.getUid());
                    userUID =  user.getUid();

                }else{
                    Log.d("onAuthStateChanged", "已登出");
                }
            }
        };
//設置原有資料
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users/"+userUID+"");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Post value = dataSnapshot.getValue(Post.class);
                pro_nickname_edit.setText(value.nickname);
                pro_realname_edit.setText(value.realname);
                pro_birthday_edit.setText(value.birthday);
                pro_phone_edit.setText(value.phone);
                if(value.sex!=null){
                switch (value.sex) {
                    case "男":
                        pro_sex_spinner.setSelection(0);
                        break;
                    case "女":
                        pro_sex_spinner.setSelection(1);
                        break;
                    case "其他":
                        pro_sex_spinner.setSelection(2);
                        break;

                    default:

                        break;

                }
                }

                //pro_sex_spinner.set
                Log.d("TAG", "Value is: " + value.email);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("AG", "Failed to read value.", error.toException());
            }
        });

        pro_sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayAdapter<CharSequence> lunchList = ArrayAdapter.createFromResource(ProfileActivity.this,
                        R.array.spn_sex,
                        android.R.layout.simple_spinner_dropdown_item);
                //pro_sex_spinner.setAdapter(lunchList);
                //Toast.makeText(ProfileActivity.this, "你選的是"+position , Toast.LENGTH_SHORT).show();
                /// sex=lunchList.getItem(position).toString();
                sex=parent.getSelectedItem().toString();

                Log.d("sex",parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }//oncreate結束


    public void change(View v){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = db.getReference("users");
        //usersRef.child(userUID).child(email);

        //性別選單



//        pro_sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                ArrayAdapter<CharSequence> lunchList = ArrayAdapter.createFromResource(ProfileActivity.this,
//                        R.array.spn_sex,
//                        android.R.layout.simple_spinner_dropdown_item);
//                //pro_sex_spinner.setAdapter(lunchList);
//                //Toast.makeText(ProfileActivity.this, "你選的是"+position , Toast.LENGTH_SHORT).show();
//                /// sex=lunchList.getItem(position).toString();
//                sex=parent.getSelectedItem().toString();
//                Log.d("sex",parent.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });



        userUID=FirebaseAuth.getInstance().getCurrentUser().getUid();//或許未來登出再登入會有問題
        usersRef.child(userUID).child("nickname").setValue(pro_nickname_edit.getText().toString());
        usersRef.child(userUID).child("realname").setValue(pro_realname_edit.getText().toString());
        usersRef.child(userUID).child("birthday").setValue(pro_birthday_edit.getText().toString());
        usersRef.child(userUID).child("phone").setValue(pro_phone_edit.getText().toString());
        usersRef.child(userUID).child("sex").setValue(sex);
        new AlertDialog.Builder(this)
                .setTitle("對話視窗")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage("修改成功")//設定顯示的文字
                .setPositiveButton("關閉視窗",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
                })//設定結束的子視窗
                .show();//呈現對話視窗





        //DatabaseReference myRef2= myRef.child("users").child(userUID);

        //listView.setAdapter(adapter);
        //Toast.makeText(ProfileActivity.this,userUID+"/"+"123",Toast.LENGTH_SHORT).show();
        /*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Toast.makeText(ProfileActivity.this,ds.child("email").getValue().toString()+userUID, Toast.LENGTH_SHORT).show();
                for (DataSnapshot ds : dataSnapshot.getChildren() ){
                   Toast.makeText(ProfileActivity.this,ds.child("email").getValue().toString()+userUID, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
        }


    @Override
    protected void onStart() {
        super.onStart();
//        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  auth.removeAuthStateListener(authListener);
    }
/* 聽說是收鍵盤的
    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
                imm.hideSoftInputFromWindow
                        (getCurrentFocus().getWindowTokeInputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }
*/
    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                pro_birthday_edit.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }
}
