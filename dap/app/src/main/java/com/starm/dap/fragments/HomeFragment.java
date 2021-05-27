package com.starm.dap.fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.starm.dap.R;
import com.starm.dap.adapters.TraingCenterAdpater;
import com.starm.dap.custom.MyMarkerView;
import com.starm.dap.models.TraingCenter;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private Spinner allcenters,weeks;
    private ArrayList<TraingCenter> traingCenters =new ArrayList<>();
    private TraingCenterAdpater mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ///views
    private RecyclerView recyclerView;

    ///charts
    private PieChart pieChart;

    private BarChart chart;

    private LineChart chartLine;
    //private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_home, container, false);
        drawBarChart(v);
        return v;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allcenters=(Spinner) view.findViewById(R.id.spiner_allcenter);
        weeks=(Spinner)view.findViewById(R.id.spiner_weesks);
        recyclerView=(RecyclerView) view.findViewById(R.id.traingcenter_list) ;
        pieChart = view.findViewById(R.id.piechart);

        initRecyclerView();
        initPieChart();
        initLineChart(view);

        /// handle spinners

//        ArrayAdapter<CharSequence> centers = ArrayAdapter.createFromResource(getContext(),
//                R.array.centers, android.R.layout.simple_spinner_item);
//        centers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        allcenters.setAdapter(centers);
//        allcenters.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
//        ArrayAdapter<CharSequence> weeksadpter = ArrayAdapter.createFromResource(getContext(),
//                R.array.weeks, android.R.layout.simple_spinner_item);
//        weeksadpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        weeks.setAdapter(weeksadpter);
//        weeks.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
    }

    private void initLineChart(View v) {
        // // Chart Style // //
        {
        chartLine = v.findViewById(R.id.chart1);

        // background color
        chartLine.setBackgroundColor(Color.WHITE);

        // disable description text
        chartLine.getDescription().setEnabled(false);

        // enable touch gestures
        chartLine.setTouchEnabled(true);

        // set listeners
        //chartLine.setOnChartValueSelectedListener((OnChartValueSelectedListener) getContext());
        chartLine.setDrawGridBackground(false);

        // create marker to display box when values are selected
        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);

        // Set the marker to the chart
        mv.setChartView(chartLine);
        chartLine.setMarker(mv);

        // enable scaling and dragging
        chartLine.setDragEnabled(true);
        chartLine.setScaleEnabled(true);
        // chart.setScaleXEnabled(true);
        // chart.setScaleYEnabled(true);

        // force pinch zoom along both axis
        chartLine.setPinchZoom(true);
    }
        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chartLine.getXAxis();

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chartLine.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chartLine.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(-50f);
        }

        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
            //llXAxis.setTypeface(tfRegular);

            LimitLine ll1 = new LimitLine(150f, "Upper Limit");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
            //ll1.setTypeface(tfRegular);

            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);
           // ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(ll2);
            //xAxis.addLimitLine(llXAxis);
        }
        // add data
//        seekBarX.setProgress(45);
//        seekBarY.setProgress(180);
        setData(15, 60);

        // draw points over time
        chartLine.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chartLine.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);

    }

    private void setData(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) - 30;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.star)));
        }

        LineDataSet set1;

        if (chartLine.getData() != null &&
                chartLine.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chartLine.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chartLine.getData().notifyDataChanged();
            chartLine.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chartLine.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chartLine.setData(data);
        }
    }


    private void initPieChart() {

        pieChart.getDescription().setEnabled(false);

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf");

        pieChart.setCenterTextTypeface(tf);
        pieChart.setCenterText(generateCenterText());
        pieChart.setCenterTextSize(10f);
        pieChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        pieChart.setHoleRadius(45f);
        pieChart.setTransparentCircleRadius(50f);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        pieChart.setData(generatePieData(tf));
    }
    protected PieData generatePieData(Typeface tf) {

        int count = 2;

        ArrayList<PieEntry> entries1 = new ArrayList<>();


            entries1.add(new PieEntry((float) (40), "Female " ));
            entries1.add(new PieEntry((float) (60), "Male " ));


        PieDataSet ds1 = new PieDataSet(entries1, "Gender Perticipate");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.WHITE);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Revenues\nQuarters 2015");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

    private void initRecyclerView() {
        initTraingingsList();
        mLayoutManager = new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mAdapter = new TraingCenterAdpater(getContext(),traingCenters);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }
    private void initTraingingsList(){
        traingCenters.add(new TraingCenter(1L,"Club RAFIKI 1", "Nyamirambo"));
        traingCenters.add(new TraingCenter(2L,"Cine Sliver", "Rwezamenyo"));
        traingCenters.add(new TraingCenter(2L,"Club MAISON DE JEUNE", "Kimisagara"));
    }
    private void drawBarChart( View v) {
        // create a new chart object
        chart = new BarChart(getActivity());
        chart.getDescription().setEnabled(false);
//        chart.setOnChartGestureListener((OnChartGestureListener) getContext());

        MyMarkerView mv = new MyMarkerView(getActivity(), R.layout.custom_marker_view);
        mv.setChartView(chart); // For bounds control
        chart.setMarker(mv);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf");

        chart.setData(generateBarData(1, 20000, 12,tf));

        Legend l = chart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        // programmatically add the chart
        FrameLayout parent = v.findViewById(R.id.parentLayout);
        parent.addView(chart);
    }
    protected BarData generateBarData(int dataSets, float range, int count,Typeface tf) {

        ArrayList<IBarDataSet> sets = new ArrayList<>();

        for(int i = 0; i < dataSets; i++) {

            ArrayList<BarEntry> entries = new ArrayList<>();

            for(int j = 0; j < count; j++) {
                entries.add(new BarEntry(j, (float) (Math.random() * range) + range / 4));
            }

            BarDataSet ds = new BarDataSet(entries, getLabel(i));
            ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
            sets.add(ds);
        }

        BarData d = new BarData(sets);
        d.setValueTypeface(tf);
        return d;
    }
    private final String[] mLabels = new String[] { "Company A", "Company B", "Company C", "Company D", "Company E", "Company F" };

    private String getLabel(int i) {
        return mLabels[i];
    }


}