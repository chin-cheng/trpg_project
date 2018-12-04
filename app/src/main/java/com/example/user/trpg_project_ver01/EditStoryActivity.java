package com.example.user.trpg_project_ver01;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.trpg_project_ver01.models.Post;
import com.example.user.trpg_project_ver01.models.Postoption;
import com.example.user.trpg_project_ver01.models.Poststory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditStoryActivity extends AppCompatActivity {
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    int chapter = 0;
    int branch = 0;
    int arroption1ch = 0;
    int arroption1br = 0;
    int arroption2ch = 0;
    int arroption2br = 0;
    int endcheck = 0;
    String key, storyname;
    private DatabaseReference writeplot;
    EditText plottext_edit, choice1text_edit, choice2text_edit;
    EditText ch1_chap, ch1_bran, ch2_chap, ch2_bran;
    TextView writeposition;
    private static final String TAG = "EditStoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("故事內容");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

//        Intent intent = this.getIntent();
//        Bundle bundle = intent.getExtras();
//        storyname = bundle.getString("storyuid");
//        Log.w(TAG, "get story uid" + storyname);

        plottext_edit = findViewById(R.id.plottext_edit);
        choice1text_edit = findViewById(R.id.choice1text_edit);
        choice2text_edit = findViewById(R.id.choice2text_edit);
        writeposition = findViewById(R.id.writeposition);


        //取Bundle
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        key = bundle.getString("key");
        Log.w(TAG, "key:" + key);
        writeplot = FirebaseDatabase.getInstance().getReference();
        chapter = bundle.getInt("chapter");
        branch = bundle.getInt("branch");

        arroption1ch = bundle.getInt("arroption1ch");
        arroption1br = bundle.getInt("arroption1br");
        arroption2ch = bundle.getInt("arroption2ch");
        arroption2br = bundle.getInt("arroption2br");
        endcheck = bundle.getInt("endcheck");
//final String position=;
        Log.w("test", "key:" + key + "ch" + chapter + "br" + branch);
        writeposition.setText(chapter + "-" + branch);


        //加入原劇情的字

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference plot = database.getReference("story/" + key + "/content/chapter/" + chapter + "/branch/" + branch);

        plot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                Poststory value = dataSnapshot.getValue(Poststory.class);

                    plottext_edit.setText(value.plot);
                    Log.d(TAG, "Value is: " + value.plot);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //加入原選項1的字
        DatabaseReference option1 = database.getReference("story/" + key + "/content/chapter/" + chapter + "/branch/" + branch + "/option/1");
        option1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                    Postoption value = dataSnapshot.getValue(Postoption.class);

                    choice1text_edit.setText(value.option_name);
                    Log.d(TAG, "option_name is: " + value.option_name);


                }
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //加入原選項2的字
        DatabaseReference option2 = database.getReference("story/" + key + "/content/chapter/" + chapter + "/branch/" + branch + "/option/2");
        option2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                    Postoption value = dataSnapshot.getValue(Postoption.class);

                    choice2text_edit.setText(value.option_name);
                    Log.d(TAG, "option_name is: " + value.option_name);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

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
                writeplot.child("story").child(key).child("content").child("chapter").child(String.valueOf(chapter)).child("branch").child(String.valueOf(branch)).child("position").setValue(chapter + "-" + branch);


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


                Toast.makeText(EditStoryActivity.this, "修改完成!", Toast.LENGTH_LONG).show();
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
