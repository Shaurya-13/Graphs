package com.example.graphs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;

public class PieChart extends AppCompatActivity {
//Setting variables
    public com.github.mikephil.charting.charts.PieChart pieChart;
    public Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
//connecting and setting variables
        pieChart=(com.github.mikephil.charting.charts.PieChart) findViewById(R.id.pieChartView);
        menu=findViewById(R.id.menuBtn);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);
//setting up the menu button
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
//making an arraylist to store data for the respective chart
        ArrayList<PieEntry> yValues=new ArrayList<>();
//data values for the demo chart
        yValues.add(new PieEntry(34f,"Monday"));
        yValues.add(new PieEntry(23f,"Tuesday"));
        yValues.add(new PieEntry(14f,"Wednesday"));
        yValues.add(new PieEntry(35f,"Thursday"));
        yValues.add(new PieEntry(40f,"friday"));
        yValues.add(new PieEntry(23f,"Saturday"));
        yValues.add(new PieEntry(25f,"Sunday"));
//styling graph
        Description description=new Description();
        description.setText("This sample Pie Chart shows expenditure over a week");
        description.setTextSize(15);
        pieChart.setDescription(description);
        pieChart.animateY(1000, Easing.EaseInOutCubic);
        PieDataSet dataSet=new PieDataSet(yValues,"Weekdays");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//setting dataset into the pie chart
        PieData pieData=new PieData(dataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);


    }
//setting up the menu function
    private void openMenu() {
        Intent intent=new Intent(this, Menu.class);
        startActivity(intent);
    }
}
