package com.example.graphs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
//Setting up buttons for menu option
    public Button lineGraph;
    public Button barGraph;
    public Button pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//connecting to button variables
        lineGraph= findViewById(R.id.line);
        barGraph=findViewById(R.id.bar);
        pieChart=findViewById(R.id.pie);
//setting up buttons
        lineGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainAct();
            }
        });
        barGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBarAct();
            }
        });
        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPieAct();
            }
        });
    }
//declaring the methods for button actions
    private void openPieAct() {
        Intent intent=new Intent(this, PieChart.class);
        startActivity(intent);
    }

    private void openBarAct() {
        Intent intent=new Intent(this, BarGraph.class);
        startActivity(intent);
    }

    private void openMainAct() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
