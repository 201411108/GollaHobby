package com.example.myapplication;

// 명연
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ResultActivity extends AppCompatActivity{

    TextView tvName;
    TextView tvFirst;
    TextView tvYou1;
    TextView tvEnd1;
    Button btnResult1;
    Button btnResult2;

    TextView tvSecond;
    TextView tvYou2;
    TextView tvEnd2;

    Button btnNext;

    RadarChart radarChart;

    ImageButton btnLogo;

    ArrayList<Integer> arr = new ArrayList<Integer>();
    HashMap<String, Integer> map = new HashMap<>();//same score

    HashMap<String, Integer> map2 = new HashMap<>();//same score

    Double num;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvName=findViewById(R.id.tvName);
        btnResult1=findViewById(R.id.btnResult1);
        btnResult2=findViewById(R.id.btnResult2);
        btnNext=findViewById(R.id.btnStart);

        btnNext=findViewById(R.id.btnStart);
        btnResult1=findViewById(R.id.btnResult1);
        btnResult2=findViewById(R.id.btnResult2);


        radarChart=findViewById(R.id.mapsearchdetail_radar_chart);

        Intent intent=getIntent();
        final String dataName = intent.getStringExtra("Name");
        final int product= intent.getIntExtra("productiontype", 0);
        final int create= intent.getIntExtra("creationtype", 0);
        final int appreciation= intent.getIntExtra("appreciationtype", 0);
        final int entertain= intent.getIntExtra("entertainmenttype", 0);
        final int analy= intent.getIntExtra("analysistype", 0);

        tvName.setText(dataName + "님");

        arr.add(product);
        arr.add(create);
        arr.add(appreciation);
        arr.add(entertain);
        arr.add(analy);

        map.put("제작형", product);
        map.put("창작형", create);
        map.put("감상형", appreciation);
        map.put("오락형", entertain);
        map.put("분석형", analy);

        map2.put("제작형", product);
        map2.put("창작형", create);
        map2.put("감상형", appreciation);
        map2.put("오락형", entertain);
        map2.put("분석형", analy);

        Collections.sort(arr);//정렬

       /* if(arr.get(4)==arr.get(3)){

            Toast.makeText(ResultActivity.this, "동점이라우", Toast.LENGTH_SHORT).show();
            num=(double)arr.get(4)+ 0.1;

            map2.put(getKey(map, arr.get(4)),num);
            *//*map2.put(getKey(map, arr.get(3)), num-0.1);*//*

            btnResult1.setText(getKey(map2, num));
            btnResult2.setText(getKey(map, arr.get(3)));
        }*/
        if(arr.get(4)==arr.get(3)){//과연 될까
            Toast.makeText(ResultActivity.this, "동점", Toast.LENGTH_SHORT).show();

            String title= getKey(map, arr.get(4));

            btnResult1.setText(getKey(map, arr.get(4)));
            map.remove(getKey(map, arr.get(4)));

            btnResult2.setText(getKey(map, arr.get(3)));
            map.put(title, arr.get(4));

        }
        else{
            btnResult1.setText(getKey(map, arr.get(4)));
            btnResult2.setText(getKey(map, arr.get(3)));
        }

        dataValue();
        makeChart();


        /*btnLogo = findViewById(R.id.btnLogo);

        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);

            }
        });*/

        /*btnResult1.setOnClickListener(new View.OnClickListener() {//첫번째 결과값 클릭이벤트
            Intent intent;
            @Override
            public void onClick(View v) {
                switch (btnResult1.getText().toString()){
                    case "분석형":
                        intent = new Intent(getApplicationContext(), SelectActivity.class);
                        startActivity(intent);
                        break;
                    case "오락형":
                        intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        break;
                    case "제작형":
                        intent = new Intent(getApplicationContext(), .class);
                        startActivity(intent);
                        break;
                    case "감상형":
                        intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        break;
                    case "창작형":
                        intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

        btnResult2.setOnClickListener(new View.OnClickListener() {//두번째 결과값 클릭이벤트
            Intent intent;
            @Override
            public void onClick(View v) {
                switch (btnResult2.getText().toString()){
                    case "분석형":
                        intent = new Intent(getApplicationContext(), SelectActivity.class);
                        startActivity(intent);
                        break;
                    case "오락형":
                        intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        break;
                    case "제작형":
                        intent = new Intent(getApplicationContext(), .class);
                        startActivity(intent);
                        break;
                    case "감상형":
                        intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        break;
                    case "창작형":
                        intent = new Intent(getApplicationContext(), LogInActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });*/

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });

    }//end of onCreate

    public static <K,V> K getKey(HashMap<K, V> map, V value){//해쉬맵에서 value값으로 key값 찾기

        for(K key: map.keySet()) {
            if (value == map.get(key)) {
                return key;
            }
        }
        return null;
    }


    public ArrayList<RadarEntry> dataValue(){
        ArrayList<RadarEntry> dataVals = new ArrayList<>();

        dataVals.add(new RadarEntry(map2.get("분석형")));
        dataVals.add(new RadarEntry(map2.get("오락형")));
        dataVals.add(new RadarEntry(map2.get("감상형")));
        dataVals.add(new RadarEntry(map2.get("제작형")));
        dataVals.add(new RadarEntry(map2.get("창작형")));

        return dataVals;
    }

    public void makeChart(){
        RadarDataSet dataSet= new RadarDataSet(dataValue(),"SCORE");
        dataSet.setColor(Color.BLUE);

        RadarData data = new RadarData();
        data.addDataSet(dataSet);
        String[] labels={"분석형", "오락형", "감상형", "제작형", "창작형"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.setData(data);
    }
}
