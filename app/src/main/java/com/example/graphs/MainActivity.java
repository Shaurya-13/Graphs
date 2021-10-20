package com.example.graphs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//creating variables
    public EditText salary, expenses;
    public Button insert;

    public Button menu;
//declaring, creating and connecting realtime database with firebase for realtime data saving and retrieving
    public LineChart lineChart;
    FirebaseDatabase salExpDatabase;
    public DatabaseReference ref;
    public LineDataSet lineDataSet=new LineDataSet(null,null);
    public ArrayList<ILineDataSet> iLineDataSets=new ArrayList<>();
    public LineData lineData;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//connecting variables to their view in xml files
        salary=findViewById(R.id.sal);
        expenses=findViewById(R.id.exp);
        insert=findViewById(R.id.insertBtn);
//creating and declaring menu button
        menu=findViewById(R.id.menuBtn);

        lineChart=findViewById(R.id.lineChartView);
        salExpDatabase=FirebaseDatabase.getInstance();
        ref=salExpDatabase.getReference("chartValues");
        insertData();
//setting onclick function to button
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

// Styling line graph
        lineDataSet.setLineWidth(4);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleColor(Color.GRAY);
        lineChart.setDrawBorders(true);
        Description description=new Description();
        description.setText("Expenditure pattern");
        description.setTextColor(Color.BLUE);
        description.setTextSize(5);
        lineChart.setDescription(description);

    }
//connecting menu button to the main menu file
    private void openMenu() {

        Intent intent=new Intent(this, Menu.class);
        startActivity(intent);

    }

//Values for generating a graph to be input by the user
    private void insertData() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id= ref.push().getKey();


                int x=Integer.parseInt(salary.getText().toString());
                int y=Integer.parseInt(expenses.getText().toString());

                DataPoint dataPoint=new DataPoint(x,y);
                ref.child(id).setValue(dataPoint);


                retrieveData();

            }
        });
    }


//Retrieving the data from the realtime database
    private void retrieveData() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry>dataValue=new ArrayList<Entry>();

                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot myDataSnapshot:dataSnapshot.getChildren()){
                        DataPoint dataPoint=myDataSnapshot.getValue(DataPoint.class);
                        dataValue.add(new Entry(dataPoint.getSalary(),dataPoint.getExpenses()));
                    }

                    showChart(dataValue);
                }
                else{
                    lineChart.clear();
                    lineChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//Saving data that is input by the user in the realtime database
    private void showChart(ArrayList<Entry> dataValue) {
        lineDataSet.setValues(dataValue);
        lineDataSet.setLabel("Sal/Exp");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        lineData=new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();
    }


}


