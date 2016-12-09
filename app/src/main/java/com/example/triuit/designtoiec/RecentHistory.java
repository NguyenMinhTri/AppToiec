package com.example.triuit.designtoiec;

/**
 * Created by TriUIT on 11/15/2016.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecentHistory extends Fragment {

    ArrayList<Detail> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String Voca[] = {"Hello -  həˈloʊ","Tools - tuːl","Debug - ˌdiːˈbʌɡ","View - vjuː","Help - help"};
    String Mean[] = {"Xin chào","Dụng cụ, đồ dùng","Sữa lỗi","Sự nhìn; tầm nhìn, tầm mắt","Sự giúp đỡ, sự cứu giúp, sự giúp ích"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listitems.clear();
        for(int i =0;i<Voca.length;i++){
            Detail item = new Detail();
            item.setMean(Mean[i]);
            item.setVoca(Voca[i]);

            listitems.add(item);
        }
        getActivity().setTitle("Detail List");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_horizontal_list_view, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Detail> list;

        public MyAdapter(ArrayList<Detail> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.titleTextView.setText(list.get(position).getVoca());
            holder.detailTextView.setText(list.get(position).getMean());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView detailTextView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            detailTextView=(TextView)v.findViewById(R.id.detailTV);
        }
    }
}