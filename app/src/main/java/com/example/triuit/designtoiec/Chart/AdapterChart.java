package com.example.triuit.designtoiec.Chart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.example.triuit.designtoiec.Demo.DayAxisValueFormatter;
import com.example.triuit.designtoiec.Demo.MyAxisValueFormatter;
import com.example.triuit.designtoiec.Demo.XYMarkerView;
import com.example.triuit.designtoiec.Question.AdapterQuestion;
import com.example.triuit.designtoiec.Question.Ques;
import com.example.triuit.designtoiec.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by TriUIT on 11/23/2016.
 */

public class AdapterChart extends ArrayAdapter<Chart> {
    List<Chart> listData;
    AdapterChart.ViewHolder viewHolder;
    Context mContext;
    protected String[] mParties = new String[] {
            "A", "B", "C", "D"};
    public AdapterChart(Context context, List<Chart> objects){
        super(context, R.layout.custom_item_chart, objects);
        listData = objects;
        mContext = context;
    }
    @Override
    public long getItemId(int position) {
        return  position;
    }
    static class ViewHolder {
        protected ImageView imgUser;
        protected TextView tvUsername,tvQues,tvLike,tvStt;
        protected BarChart mChart;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.custom_item_chart, null);

            viewHolder = new AdapterChart.ViewHolder();
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.tvLike = (TextView) convertView.findViewById(R.id.tvLike);
            viewHolder.tvQues = (TextView) convertView.findViewById(R.id.tvQues);
            viewHolder.tvStt = (TextView) convertView.findViewById(R.id.tvStt);
            viewHolder.imgUser = (ImageView) convertView.findViewById(R.id.imgUser);
            viewHolder.mChart = (BarChart) convertView.findViewById(R.id.mChart);



            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvUsername, viewHolder.tvUsername);
            convertView.setTag(R.id.tvLike, viewHolder.tvLike);
            convertView.setTag(R.id.tvQues, viewHolder.tvQues);
            convertView.setTag(R.id.tvStt, viewHolder.tvStt);
            convertView.setTag(R.id.imgUser, viewHolder.imgUser);
            convertView.setTag(R.id.mChart, viewHolder.mChart);


        } else {
            viewHolder = (AdapterChart.ViewHolder) convertView.getTag();
        }
        //set tag

        //set data
        viewHolder.tvStt.setText(listData.get(position).getStt());
        viewHolder.tvStt.setTextColor(Color.parseColor(getRandomColor()));
        viewHolder.tvUsername.setText(listData.get(position).getUser());

        viewHolder.imgUser.setImageResource(listData.get(position).getImgUser());
        //set image
        viewHolder.mChart.setDrawBarShadow(false);
        viewHolder.mChart.setDrawValueAboveBar(true);

        viewHolder.mChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        viewHolder.mChart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        viewHolder.mChart.setPinchZoom(false);

        viewHolder.mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(viewHolder.mChart);

        XAxis xAxis = viewHolder.mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = viewHolder.mChart.getAxisLeft();

        leftAxis.setLabelCount(3, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setTextSize(3f);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(3f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = viewHolder.mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        rightAxis.setLabelCount(3, false);
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(3f);
        rightAxis.setTextSize(3f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)


        // l.setExtra(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });
        // l.setCustom(ColorTemplate.VORDIPLOM_COLORS, new String[] { "abc",
        // "def", "ghj", "ikl", "mno" });

        XYMarkerView mv = new XYMarkerView(mContext, xAxisFormatter);
        mv.setChartView(viewHolder.mChart); // For bounds control
        viewHolder.mChart.setMarker(mv); // Set the marker to the chart

        setData(6, 90);

        // setting data




        viewHolder.mChart.getLegend().setEnabled(false);

        return convertView;
    }
    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = (int) start; i < start + count + 1; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            yVals1.add(new BarEntry(i, val));
        }

        BarDataSet set1;

        if ( viewHolder.mChart.getData() != null &&
                viewHolder.mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet)  viewHolder.mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            viewHolder.mChart.getData().notifyDataChanged();
            viewHolder.mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "The year 2017");
            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);


            data.setBarWidth(1f);

            viewHolder.mChart.setData(data);
        }
    }
    public String getRandomColor() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#ED7D31");
        colors.add("#00B0F0");
        colors.add("#FF0000");
        colors.add("#D0CECE");
        colors.add("#00B050");
        colors.add("#9999FF");
        colors.add("#FF5FC6");
        colors.add("#FFC000");
        colors.add("#7F7F7F");
        colors.add("#4800FF");

        return colors.get(new Random().nextInt(colors.size()));
    }
}
