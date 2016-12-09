package com.example.triuit.designtoiec.Dictionary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.triuit.designtoiec.R;

/**
 * Created by TriUIT on 11/22/2016.
 */

public class Tab_VA extends Fragment {

    public static DataAdapter mAdapterVA;
    public static ListView mListViewVA;
    private EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_anh_viet, container, false);

        mListViewVA=(ListView)view.findViewById(R.id.listView);




        editText=(EditText)view.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                mAdapterVA.getFilter().filter(cs);
                mAdapterVA.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;

    }
}
