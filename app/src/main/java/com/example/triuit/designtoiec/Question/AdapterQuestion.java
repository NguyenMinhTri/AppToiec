package com.example.triuit.designtoiec.Question;

/**
 * Created by TriUIT on 11/16/2016.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.example.triuit.designtoiec.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.x;


/**
 * Created by nmtri_000 on 12/29/2015.
 */
public class AdapterQuestion  extends ArrayAdapter<Ques> {
    List<Ques> listData;
    ViewHolder viewHolder;
    Context mContext;
    protected String[] mParties = new String[] {
                      "A", "B", "C", "D"};
    public AdapterQuestion(Context context, List<Ques> objects){
        super(context, R.layout.custom_item_question, objects);
        listData = objects;
        mContext = context;
    }
    @Override
    public long getItemId(int position) {
        return  position;
    }
    static class ViewHolder {
        protected ImageView imgUser,imgQues;
        protected TextView tvUsername,tvDate,tvQues,tvD,tvQues2;
        protected RadioButton rbA,rbB,rbC;
        protected TagView tagGroup;
        protected PieChart mChart;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.custom_item_question, null);

            viewHolder = new ViewHolder();
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.name);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.timestamp);
            viewHolder.tvQues = (TextView) convertView.findViewById(R.id.txtStatusMsg);
            viewHolder.rbA = (RadioButton) convertView.findViewById(R.id.rbCauA);
            viewHolder.rbB = (RadioButton) convertView.findViewById(R.id.rbCauB);
            viewHolder.rbC = (RadioButton) convertView.findViewById(R.id.rbCauC);

            viewHolder.tagGroup=(TagView) convertView.findViewById(R.id.tag_group);

            viewHolder.imgUser = (ImageView) convertView.findViewById(R.id.profilePic);
            viewHolder.mChart = (PieChart) convertView.findViewById(R.id.chart1);



            convertView.setTag(viewHolder);
            convertView.setTag(R.id.name, viewHolder.tvUsername);
            convertView.setTag(R.id.timestamp, viewHolder.tvDate);
            convertView.setTag(R.id.txtStatusMsg, viewHolder.tvQues);
            convertView.setTag(R.id.rbCauA, viewHolder.tvQues2);
            convertView.setTag(R.id.profilePic, viewHolder.imgUser);
            convertView.setTag(R.id.profilePic, viewHolder.imgUser);

            convertView.setTag(R.id.chart1, viewHolder.mChart);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //set tag

        //set data
        viewHolder.tvDate.setText(listData.get(position).getTime().toString());
        viewHolder.tvUsername.setText(listData.get(position).getUser());
        viewHolder.rbA.setText(listData.get(position).getA());
        viewHolder.rbB.setText(listData.get(position).getB());
        viewHolder.rbC.setText(listData.get(position).getC());

        viewHolder.imgUser.setImageResource(listData.get(position).getImgUser());
        //set image
        ArrayList<Tag> tags = new ArrayList<>();
        Tag tag;


        for (int i = 0; i < 3; i++) {

            tag = new Tag("hashtag");
            tag.radius = 10f;
            tag.layoutColor = Color.parseColor(getRandomColor());
            if (i % 2 == 0) // you can set deletable or not
                tag.isDeletable = true;
            tags.add(tag);

        }
        viewHolder.tagGroup.addTags(tags);


        viewHolder.mChart.setUsePercentValues(true);


        viewHolder.mChart.setDragDecelerationFrictionCoef(0.95f);
        viewHolder.mChart.getDescription().setEnabled(false);




        viewHolder.mChart.setDrawHoleEnabled(true);
        viewHolder.mChart.setHoleColor(Color.TRANSPARENT);

        viewHolder.mChart.setTransparentCircleColor(Color.TRANSPARENT);
        viewHolder.mChart.setTransparentCircleAlpha(0);

        viewHolder.mChart.setHoleRadius(10f);
        viewHolder.mChart.setTransparentCircleRadius(21f);

        viewHolder.mChart.setCenterTextColor(Color.BLUE);
        viewHolder.mChart.setEntryLabelColor(Color.RED);
        // mChart.setUnit(" €");
        viewHolder.mChart.setEntryLabelTextSize(8f);
        // mChart.setDrawUnitsInChart(true);

        setData(4, 100);

        // mChart.spin(2000, 0, 360);
        viewHolder.mChart.getLegend().setEnabled(false);





        return convertView;
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

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5), mParties[i % mParties.length]));
        }
        float temp=  entries.get(0).getY();
        float x=0;
        for (int i=0;i<count;i++)
        {
            if(temp<=entries.get(i).getY())
            {
                temp=entries.get(i).getY();
                x=i;
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(5f);
        data.setValueTextColor(Color.BLACK);

        viewHolder.mChart.setData(data);
        viewHolder.mChart.getHighlighted();


        // undo all highlights
        viewHolder.mChart.highlightValues(null);

        viewHolder.mChart.invalidate();
        viewHolder.mChart.highlightValue(x,temp,0);
    }

}
