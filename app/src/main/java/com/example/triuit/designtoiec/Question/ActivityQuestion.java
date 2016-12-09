package com.example.triuit.designtoiec.Question;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.example.triuit.designtoiec.R;

import java.util.ArrayList;

/**
 * Created by TriUIT on 11/16/2016.
 */

public class ActivityQuestion  extends AppCompatActivity {
    ListView listView;

    AdapterQuestion mAdapter;
    ArrayList<Ques> listitems = new ArrayList<>();
    String User[] = {"Thu Thảo","Quân Trương Minh","Tam Nguyễn","Trung Nguyễn","Huyen Nguyen"};
    String Time[] = {"2 hrs","15 mins","9 hrs","55 mins","9 hrs"};
    String Ques[] ={"The introduction of new fertilizer has dramatically increased the ..... of local farms, especially those that are growing fruit."
            ,"it is essential that he......his term paper by the end of next week"
            ,"Some of the criminals were arrested ...... the border"
            ,"Brian was seen .... his car "
            ,"Physicians can look into the lungs, heart and other areas that were formerly ............... to them."};
    int imgUser[]={R.drawable.speak,R.drawable.speak,R.drawable.speak,R.drawable.speak,R.drawable.speak};
    String A[] = {"A. Location","A. Submit","A. Cross","A. washing ","A. accessible"};
    String B[] = {"B. Output","B. Submits","B. To cross","B. wash ","B. access"};
    String C[] = {"C. Preparation","C. Submitted","C. Crossed","C. have washed ","C. accessibly"};
    String D[] = {"D. Rules","D. has submitted","D. Crossing","D. washed","D. inaccessible"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Các câu hỏi");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        listView=(ListView)findViewById(R.id.listQuestion);

        listitems.clear();
        for(int i =0;i<Time.length;i++){
            Ques item = new Ques();
            item.setUser(User[i]);
            item.setTime(Time[i]);
            item.setQues(Ques[i]);
            item.setA(A[i]);
            item.setB(B[i]);
            item.setC(C[i]);
            item.setD(D[i]);
            item.setImgUser(imgUser[i]);

            listitems.add(item);
        }
        mAdapter=new AdapterQuestion(getApplicationContext(),listitems);
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
