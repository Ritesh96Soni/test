package com.example.lenovo.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class second extends AppCompatActivity {

    private GraphView graphView;
    private double values[];
    private double val1[];
    private double val2[];
    private double val3[];
    private double val4[];
    private LineGraphSeries<DataPoint> series1, series2,series3,series4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                graphView = (GraphView) findViewById(R.id.graph);
                graphView.getViewport().setXAxisBoundsManual(true);
                graphView.getViewport().setScalable(true);
                graphView.getViewport().setScrollable(true);
                graphView.getViewport().setMinX(0);
                graphView.getViewport().setMaxX(50);

                Double[] arr = arrayList.toArray(new Double[arrayList.size()]);
                values = new double[arr.length];
                for (int j = 0; j < arr.length; j++) {
                    values[j] = arr[j];
                }
                series1 = new LineGraphSeries<>();
                series1.setColor(Color.RED);
                series2 = new LineGraphSeries<>();
                series2.setColor(Color.RED);
                series3 = new LineGraphSeries<>();
                series3.setColor(Color.RED);
                series4 = new LineGraphSeries<>();
                series4.setColor(Color.RED);

                for (int i = 0; i < values.length; i++) {
                    series.appendData(new DataPoint(i, values[i]), true, 100);
                }
                for (int i = 0; i < val.length; i++) {
                    filtered.appendData(new DataPoint(i, val[i]), true, 100);
                }
                graphView.addSeries(series);
                graphView.addSeries(filtered);
                graphView.getViewport().setScalable(true);
                graphView.getViewport().setScrollable(true);
                graphView.getViewport().setScalableY(true);
                graphView.getViewport().setScrollableY(true);
            }
        });
    }

}
