package com.example.triuit.designtoiec.Toiec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.triuit.designtoiec.Activity_Home;
import com.example.triuit.designtoiec.MainActivity;
import com.example.triuit.designtoiec.R;

import java.util.ArrayList;

/**
 * Created by TriUIT on 11/18/2016.
 */

public class ActivityDetailToiec extends AppCompatActivity {
    ListView lvToiec;
    AdapterToiec mAdapter;
    ArrayList<Toiec> listitems = new ArrayList<>();
    int [] img={R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.ssix,R.drawable.seven};
    String [] name={"Part I","Part II","Part III","Part IV","Part V","Part VI","Part VII","Part VI"};
    String [] detail={"Photographs" ,"Question & Response","Short Conversations","Short Talks","Incomplete Sentences","Text Completion","Reading Comprehension"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_part_toiec);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Toiec 250- 500");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lvToiec=(ListView)findViewById(R.id.lvToiec);

        for(int i =0;i<7;i++){
            Toiec item = new Toiec();
            item.setImg(img[i]);
            item.setNamePart(name[i]);
            item.setPart(detail[i]);
            listitems.add(item);
        }
        mAdapter=new AdapterToiec(getApplicationContext(),listitems);
        lvToiec.setAdapter(mAdapter);
        lvToiec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    startActivity(new Intent(ActivityDetailToiec.this, Activity_Part1.class));
                }
                if(i==1)
                {
                    startActivity(new Intent(ActivityDetailToiec.this, Activity_Part2.class));
                }
                if(i==2)
                {
                    startActivity(new Intent(ActivityDetailToiec.this, Activity_Part3.class));
                }
            }
        });

    }
}