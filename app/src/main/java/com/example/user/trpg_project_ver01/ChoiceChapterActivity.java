package com.example.user.trpg_project_ver01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ChoiceChapterActivity extends AppCompatActivity {
    private static final String TAG = "ChoiceChapterActivity";
    ListView lv_showchapter;
    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_chapter);
        lv_showchapter=findViewById(R.id.lv_showchapter);
        String[]func={"0-0","1-1","1-2","2-1","2-2","2-3","2-4"};
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        key=bundle.getString("key");
        Toast.makeText(ChoiceChapterActivity.this, key, Toast.LENGTH_LONG).show();




        ArrayAdapter adapter =new ArrayAdapter(this,android.R.layout.simple_list_item_1,func);
        lv_showchapter.setAdapter(adapter);

        lv_showchapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.w("lv",position+"");
                Intent intent=new Intent();
                intent.setClass(ChoiceChapterActivity.this,AddBranchActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("key",key);

                switch (position){
                    case 0://0-0

                        bundle.putInt("chapter",0);
                        bundle.putInt("branch",0);

                        break;
                    case 1://1-1
                        bundle.putInt("chapter",1);
                        bundle.putInt("branch",1);
                        break;

                    case 2://1-2
                        bundle.putInt("chapter",1);
                        bundle.putInt("branch",2);
                        break;

                    case 3://2-1
                        bundle.putInt("chapter",2);
                        bundle.putInt("branch",1);
                        break;

                    case 4://2-2
                        bundle.putInt("chapter",2);
                        bundle.putInt("branch",2);
                        break;

                    case 5://2-3
                        bundle.putInt("chapter",2);
                        bundle.putInt("branch",3);
                        break;

                    case 6://2-4
                        bundle.putInt("chapter",2);
                        bundle.putInt("branch",4);
                        break;

                    default:
                        Log.w("lv","error");
                        break;


                }

                intent.putExtras(bundle);
                startActivity(intent);
                // Toast.makeText(MemberActivity.this,"第"+(position+1)+"條",Toast.LENGTH_SHORT).show();
            }
        });
    }
}