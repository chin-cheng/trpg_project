package com.example.user.trpg_project_ver01;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddBranchActivity extends AppCompatActivity {
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    int chapter=0;
    int branch=0;
    int arroption1ch=0;
    int arroption1br=0;
    int arroption2ch=0;
    int arroption2br=0;
    int endcheck=0;
    String key;
    private DatabaseReference writeplot;
    EditText plottext_edit,choice1text_edit,choice2text_edit;
    EditText ch1_chap,ch1_bran,ch2_chap,ch2_bran;
    TextView writeposition,choice1text,choice2text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);
        plottext_edit=findViewById(R.id.plottext_edit);
        choice1text_edit=findViewById(R.id.choice1text_edit);
        choice2text_edit=findViewById(R.id.choice2text_edit);
        writeposition=findViewById(R.id.writeposition);
        choice1text=findViewById(R.id.choice1text);
        choice2text=findViewById(R.id.choice2text);


        //取Bundle
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        key=bundle.getString("key");
        writeplot= FirebaseDatabase.getInstance().getReference();
        chapter=bundle.getInt("chapter");
        branch=bundle.getInt("branch");

        arroption1ch=bundle.getInt("arroption1ch");
        arroption1br=bundle.getInt("arroption1br");
        arroption2ch=bundle.getInt("arroption2ch");
        arroption2br=bundle.getInt("arroption2br");
        endcheck=bundle.getInt("endcheck");
//final String position=;
        Log.w("test","key:"+key+"ch"+chapter+"br"+branch);
        writeposition.setText(chapter+"-"+branch);
if(chapter==2){
    choice1text_edit.setVisibility(View.INVISIBLE);
    choice2text_edit.setVisibility(View.INVISIBLE);
    choice1text.setVisibility(View.INVISIBLE);
    choice2text.setVisibility(View.INVISIBLE);
}
        //Toast.makeText(AddBranchActivity.this, chapter+"-"+branch, Toast.LENGTH_LONG).show();

        findViewById(R.id.fabNewPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //branch
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("branch").setValue(branch);
                //chapter
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("chapter").setValue(chapter);
                //endcheck
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("endcheck").setValue(endcheck);
                //plot
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("plot").setValue(plottext_edit.getText().toString());
                //position
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("position").setValue(chapter+"-"+branch);


                //option1

                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("1").child("option_name").setValue(choice1text_edit.getText().toString());
                // option1arrchapter
                //writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("1").child("arrive_chapter").setValue(ch1_chap.getText().toString());
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("1").child("arrive_chapter").setValue(arroption1ch);

                //option1arrbranch
                //writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("1").child("arrive_branch").setValue(ch1_bran.getText().toString());
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("1").child("arrive_branch").setValue(arroption1br);
                //optionposition
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("1").child("option_position").setValue(1);



                //option2

                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("2").child("option_name").setValue(choice2text_edit.getText().toString());
                // option2arrchapter
                //writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("2").child("arrive_chapter").setValue(ch2_chap.getText().toString());
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("2").child("arrive_chapter").setValue(arroption2ch);

                //option2arrbranch
                //writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("2").child("arrive_branch").setValue(ch2_bran.getText().toString());
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("2").child("arrive_branch").setValue(arroption2br);
                //optionposition
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("option").child("2").child("option_position").setValue(2);


                Toast.makeText(AddBranchActivity.this, "新增成功", Toast.LENGTH_LONG).show();
                finish();
            }
        });


/*
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {


            };
            private final String[] mFragmentNames = new String[] {
                    getString(R.string.heading_recent),
                    getString(R.string.heading_my_posts),
                    getString(R.string.heading_my_top_posts)
            };
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            @Override
            public int getCount() {
                return mFragments.length;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }
        };
*/
        // Set up the ViewPager with the sections adapter.

        /*
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
*/
        // Button launches NewPostActivity


    }


}
