package com.example.graphs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BarGraph extends AppCompatActivity {
//setting up variables
    public BarChart barChart;
    public Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);
//setting up menu button
        menu=findViewById(R.id.menuBtn);
//setting up barchart
        barChart = (BarChart) findViewById(R.id.barChartView);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
        barChart.animateY(2000);
        barChart.invalidate();
//setting up onclick function for menu button
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });
//default data list for sample chart
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 40f));
        barEntries.add(new BarEntry(2, 44f));
        barEntries.add(new BarEntry(3, 30f));
        barEntries.add(new BarEntry(4, 36f));
//styling the chart
        Description description=new Description();
        description.setText("This sample Bar Chart shows expenditure over a year");
        description.setTextSize(12);
        barChart.setDescription(description);

        BarDataSet barDataSet = new BarDataSet(barEntries, "DataSet-1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);

        barChart.setData(data);

        String[] months=new String[]{"Jan","Feb","Mar","April","May","Jun"};
        XAxis xAxis=barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);

    }
//setting up menu function to connect to the menu file/activity
    private void openMenu() {

        Intent intent=new Intent(this, Menu.class);
        startActivity(intent);

    }
//setting up the values for the chart
    public class MyXAxisValueFormatter extends ValueFormatter implements IAxisValueFormatter{

            private String[] mValues;
            public MyXAxisValueFormatter(String[] values) {

                this.mValues=values;

            }

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mValues[(int)value];
            }
        }



}
