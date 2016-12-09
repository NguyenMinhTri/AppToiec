package com.example.triuit.designtoiec.Toiec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.triuit.designtoiec.Question.AdapterQuestion;
import com.example.triuit.designtoiec.Question.Ques;
import com.example.triuit.designtoiec.R;

import java.util.List;

/**
 * Created by TriUIT on 11/18/2016.
 */

public class AdapterToiec extends ArrayAdapter<Toiec> {
    List<Toiec> listData;
    ViewHolder viewHolder;
    Context mContext;
    public AdapterToiec(Context context, List<Toiec> objects){
        super(context, R.layout.custom_item_toiec, objects);
        listData = objects;
        mContext = context;
    }
    @Override
    public long getItemId(int position) {
        return  position;
    }
    static class ViewHolder {
        protected ImageView img;
        protected TextView Name, detail;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.custom_item_toiec, null);

            viewHolder = new ViewHolder();
            viewHolder.Name = (TextView) convertView.findViewById(R.id.toiecName);
            viewHolder.detail = (TextView) convertView.findViewById(R.id.toiecDetail);


            viewHolder.img = (ImageView) convertView.findViewById(R.id.toiecImg);




            convertView.setTag(viewHolder);
            convertView.setTag(R.id.toiecName, viewHolder.Name);
            convertView.setTag(R.id.toiecDetail, viewHolder.detail);
            convertView.setTag(R.id.toiecImg, viewHolder.img);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //set tag

        //set data
        viewHolder.Name.setText(listData.get(position).getNamePart());
        viewHolder.detail.setText(listData.get(position).getPart());
        viewHolder.img.setImageResource(listData.get(position).getImg());
        //set image



        return convertView;
    }

}
