package com.example.user.trpg_project_ver01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.trpg_project_ver01.models.Postoption;
import com.example.user.trpg_project_ver01.models.Poststory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlayVer2Activity extends AppCompatActivity {
    private static String TAG = "PlayVer2Activity";
    private String userUID;
    Button choice1, choice2, start;
    long chapter = 0;
    long branch = 0;
    long endcheck = 0;
    String storyname = "storyname";
    TextView test;
    DatabaseReference plot = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch);
    DatabaseReference option1 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/1");
    DatabaseReference option2 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/2");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ver2);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        storyname=bundle.getString("storyuid");
        Log.w(TAG, "get story uid"+storyname);

        userUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        test = findViewById(R.id.test);
        plot = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch);
        option1 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/1");
        option2 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/2");
        Log.w(TAG, "readposition inside ch" + chapter + "br" + branch);
        //劇情listemer
        plot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Poststory value = snapshot.getValue(Poststory.class);
                test.append(value.plot + "\n");
                endcheck = value.endcheck;
                Log.w(TAG, "endcheck" + endcheck);
                //如果ecdcheck!=999就讀取選項文字
                if (endcheck != 999) {
                    //按鈕1文字
                    option1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            Postoption value = snapshot.getValue(Postoption.class);
                            choice1.setText(value.option_name);

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });

                    //按鈕2文字
                    option2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            Postoption value = snapshot.getValue(Postoption.class);
                            choice2.setText(value.option_name);

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w(TAG, "Failed to read value.", error.toException());
                        }
                    });

                    Log.w(TAG, "readoptiontext-ch" + chapter + "br" + branch);


                } else {
                    choice1.setVisibility(View.INVISIBLE);
                    choice2.setVisibility(View.INVISIBLE);
                    Log.w(TAG, "story end");


                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        //劇情listemer結束


        //按鈕1setOnClickListener
        choice1.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {

                option1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Postoption value = snapshot.getValue(Postoption.class);
                        // 讀取選項一應該到的位置
                        chapter = value.arrive_chapter;
                        branch = value.arrive_branch;

                        plot = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch);
                        option1 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/1");
                        option2 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/2");
                        Log.w(TAG, "readposition inside ch" + chapter + "br" + branch);
                        //劇情addValueEventListener
                        plot.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                Poststory value = snapshot.getValue(Poststory.class);
                                //取劇情跟檢查碼
                                test.append(value.plot + "\n");
                                endcheck = value.endcheck;
                                Log.w(TAG, "endcheck" + endcheck);
                                //如果ecdcheck!=999就讀取選項文字
                                if (endcheck != 999) {
                                    option1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            Postoption value = snapshot.getValue(Postoption.class);
                                            choice1.setText(value.option_name);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w(TAG, "Failed to read value.", error.toException());
                                        }
                                    });

                                    option2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            Postoption value = snapshot.getValue(Postoption.class);
                                            choice2.setText(value.option_name);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w(TAG, "Failed to read value.", error.toException());
                                        }
                                    });
                                    Log.w(TAG, "readoptiontext-ch" + chapter + "br" + branch);


                                } else {
                                    choice1.setVisibility(View.INVISIBLE);
                                    choice2.setVisibility(View.INVISIBLE);
                                    Log.w(TAG, "story end");

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.w(TAG, "Failed to read value.", error.toException());
                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }

        });//按鈕1listener結束

        //按鈕2listener
        choice2.setOnClickListener(new Button.OnClickListener() {


            @Override
            public void onClick(View v) {

                option2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        Postoption value = snapshot.getValue(Postoption.class);
                        // 讀取選項一應該到的位置
                        chapter = value.arrive_chapter;
                        branch = value.arrive_branch;

                        plot = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch);
                        option1 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/1");
                        option2 = FirebaseDatabase.getInstance().getReference("story/" + storyname + "/content/chapter/" + chapter + "/branch/" + branch + "/option/2");
                        Log.w(TAG, "readposition inside ch" + chapter + "br" + branch);
                        //劇情addValueEventListener
                        plot.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                Poststory value = snapshot.getValue(Poststory.class);
                                //取劇情跟檢查碼
                                test.append(value.plot + "\n");
                                endcheck = value.endcheck;
                                Log.w(TAG, "endcheck" + endcheck);
                                //如果ecdcheck!=999就讀取選項文字
                                if (endcheck != 999) {
                                    option1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            Postoption value = snapshot.getValue(Postoption.class);
                                            choice1.setText(value.option_name);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w(TAG, "Failed to read value.", error.toException());
                                        }
                                    });

                                    option2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot snapshot) {
                                            Postoption value = snapshot.getValue(Postoption.class);
                                            choice2.setText(value.option_name);

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w(TAG, "Failed to read value.", error.toException());
                                        }
                                    });
                                    Log.w(TAG, "readoptiontext-ch" + chapter + "br" + branch);


                                } else {
                                    choice1.setVisibility(View.INVISIBLE);
                                    choice2.setVisibility(View.INVISIBLE);
                                    Log.w(TAG, "story end");

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                Log.w(TAG, "Failed to read value.", error.toException());
                            }
                        });

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


            }

        });
        //按鈕2listener結束


    }
}
