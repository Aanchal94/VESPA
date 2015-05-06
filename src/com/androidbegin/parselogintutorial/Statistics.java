package com.androidbegin.parselogintutorial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

public class Statistics extends Activity {
    int[] firstData={23,145,67,78};
    int[] secondData={83,45,168,138};
    private static final ParseQuery<ParseUser> ParseClass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("votes");
        Button genBarChart=(Button)findViewById(R.id.generatePieChart);
        genBarChart.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                getBarChart();
            }
        });
     }

    public void getBarChart(){
        XYMultipleSeriesRenderer barChartRenderer = getBarChartRenderer();
        setBarChartSettings(barChartRenderer);
        Intent intent = ChartFactory.getBarChartIntent(this, getBarDemoDataset(), barChartRenderer, BarChart.Type.DEFAULT);
        startActivity(intent);
    }

    private XYMultipleSeriesDataset getBarDemoDataset() {
        XYMultipleSeriesDataset barChartDataset = new XYMultipleSeriesDataset();
        CategorySeries firstSeries = new CategorySeries("Growth of Company1");
        for(int i=0;i<firstData.length;i++)
            firstSeries.add(firstData[i]);
        barChartDataset.addSeries(firstSeries.toXYSeries());

        CategorySeries secondSeries = new CategorySeries("Growth of Company2");
        for(int j=0;j<secondData.length;j++)
            secondSeries.add(secondData[j]);
        barChartDataset.addSeries(secondSeries.toXYSeries());
        return barChartDataset;
    }

    public XYMultipleSeriesRenderer getBarChartRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(20);
        renderer.setChartTitleTextSize(18);
        renderer.setLabelsTextSize(18);
        renderer.setLegendTextSize(18);
        renderer.setMargins(new int[] {20, 30, 15, 0});
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(Color.BLUE);
        renderer.addSeriesRenderer(r);
        r = new SimpleSeriesRenderer();
        r.setColor(Color.GREEN);
        renderer.addSeriesRenderer(r);
        return renderer;
    }
    private void setBarChartSettings(XYMultipleSeriesRenderer renderer) {
        renderer.setChartTitle("Growth comparison company1 vs company2");
        renderer.setXTitle("No of Years in industry");
        renderer.setYTitle("Profit in millions");
        renderer.setXAxisMin(0.5);
        renderer.setXAxisMax(10.5);
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(210);
    }
}
