package com.example.triuit.designtoiec.Chart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.triuit.designtoiec.Question.AdapterQuestion;
import com.example.triuit.designtoiec.Question.Ques;
import com.example.triuit.designtoiec.R;

import java.util.ArrayList;

/**
 * Created by TriUIT on 11/23/2016.
 */

public class ActivityChart extends AppCompatActivity {
    ListView listView;

    AdapterChart mAdapter;
    ArrayList<Chart> listitems = new ArrayList<>();
    String User[] = {"Thu Thao","Quan Truong","Tam Nguyen","Trung Nguyen","Huyen Nguyen","Thu Thao","Quân Truong","Tam Nguyen","Trung Nguyen","Huyen Nguyen"};
    String Like[] = {"2 hrs","15 mins","9 hrs","55 mins","9 hrs"};
    String Ques[] ={"2 hrs","15 mins","9 hrs","55 mins","9 hrs"};
    String STT[] ={"1","2","3","4","5","6","7","8","9","10"};
    int number[]={1,2,3,4,5,6,7,8,9,10};
    int imgUser[]={R.drawable.speak,R.drawable.speak,R.drawable.speak,R.drawable.speak,R.drawable.speak,R.drawable.write,R.drawable.listen,R.drawable.speak,R.drawable.listen,R.drawable.listen};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bảng Xếp Hạng");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        listView=(ListView)findViewById(R.id.listQuestion);

        listitems.clear();
        for(int i =0;i<9;i++){
            Chart item = new Chart();
            item.setUser(User[i]);
            item.setStt(STT[i]);
            item.setQues(number[i]);
            item.setLike(number[i]);
            item.setImgUser(imgUser[i]);
            listitems.add(item);
        }
        mAdapter=new AdapterChart(getApplicationContext(),listitems);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



    }



    protected void onDestroy() {
        super.onDestroy();
        //hủy bỏ đăng ký khi tắt ứng dụng


    }
    @Override
    protected void onPause()
    {
        super.onPause();
        //closing transition animations
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }
}
