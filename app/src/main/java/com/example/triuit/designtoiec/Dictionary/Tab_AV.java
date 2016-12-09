package com.example.triuit.designtoiec.Dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.triuit.designtoiec.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TriUIT on 11/22/2016.
 */

public class Tab_AV extends Fragment {

    public static String dataFullAV;
    public static String dataVocabulary;
    StringBuilder _dataFullAV;
    StringBuilder _dataVocabulary;

    public  DataAdapter mAdapterAV;
    public List<DataOffline> mDataAV;
    public  List<DataOffline> mDataVA=new ArrayList<>();

    public static ListView mListView;
    private EditText editText;


    public Tab_AV()
    {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_anh_viet, container, false);
        mListView=(ListView)view.findViewById(R.id.listView);
        mDataAV=new ArrayList<>();


        editText=(EditText)view.findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                mAdapterAV.getFilter().filter(cs);

                mAdapterAV.notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view.findViewById(R.id.tvVoca)).getText().toString();
                Intent acPop = new Intent(getActivity(), Popup_TraTu.class);
                acPop.putExtra("Value", ((TextView) view.findViewById(R.id.tvVoca)).getText().toString().replace(" /", " ☺/") + "\n" + ((TextView) view.findViewById(R.id.tvMean)).getText().toString());
                startActivity(acPop);

                //Toast.makeText(getActivity(), ((TextView) view.findViewById(R.id.tvVoca)).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        readData();

                    }
                });


            }
        }).start();
        return  view;

    }

    public void readData()
    {
        _dataFullAV=new StringBuilder();
        _dataVocabulary=new StringBuilder();
        //Doc Anh Viet
        Log.v("Doc Lai Du Lieu", "..............................................");
        String data;
        InputStream in= getResources().openRawResource(R.raw.anhviet);
        InputStreamReader inreader=new InputStreamReader(in);
        BufferedReader bufreader=new BufferedReader(inreader);
        StringBuilder readAll=new StringBuilder();
        int dem=0;
        StringBuilder builder=new StringBuilder();
        //Doc Viet Anh
        String data2=" ";
        InputStream in2= getResources().openRawResource(R.raw.vietanh);
        InputStreamReader inreader2=new InputStreamReader(in2);
        BufferedReader bufreader2=new BufferedReader(inreader2);
        boolean checkVA=true;
        StringBuilder readAll2=new StringBuilder();
        int dem2=0;
        StringBuilder builder2=new StringBuilder();
        //
        if(in!=null)
        {
            try
            {
                while(((data=bufreader.readLine())!=null) )
                {
                    _dataFullAV.append(data+"\n");

                    try {
                        if (data.indexOf("@") != -1) {
                            if (dem == 1) {
                                _dataVocabulary.append(data + "\n");
                                String TuVung = String.valueOf(builder);
                                String Nghia = String.valueOf(readAll);


                                mDataAV.add(new DataOffline(TuVung.substring(1), Nghia));
                                readAll = new StringBuilder();
                                builder = new StringBuilder();
                                dem = 0;
                            }

                            builder.append(data);
                            dem = 1;

                        } else {
                            readAll.append(data);

                            readAll.append("\n");
                        }
                    }
                    catch (Exception e){}

                    try {
                        if(checkVA)
                            if((data2=bufreader2.readLine())!=null) {
                                if (data2.indexOf("@") != -1) {
                                    if (dem2 == 1) {
                                        String TuVung = String.valueOf(builder2);
                                        String Nghia = String.valueOf(readAll2);

                                        mDataVA.add(new DataOffline(TuVung.substring(1), Nghia));
                                        readAll2 = new StringBuilder();
                                        builder2 = new StringBuilder();
                                        dem2 = 0;
                                    }

                                    builder2.append(data2);
                                    dem2 = 1;

                                } else {
                                    readAll2.append(data2);

                                    readAll2.append("\n");
                                }
                            }
                            else
                            {
                                checkVA=false;
                            }
                    }
                    catch (Exception e){}

                }

                dataFullAV=_dataFullAV.toString();
                dataVocabulary=_dataVocabulary.toString();
                in.close();
                in2.close();
                //  mAdapterAV.addAll(mDataAV);
                mAdapterAV = new DataAdapter(getActivity(), mDataAV,false);
                mListView.setAdapter(mAdapterAV);

                Tab_VA.mAdapterVA = new DataAdapter(getActivity(), mDataVA,true);
                Tab_VA.mListViewVA.setAdapter(Tab_VA.mAdapterVA);



            }
            catch(IOException ex){
                Log.e("ERROR", ex.getMessage());
            }
        }
    }
}
