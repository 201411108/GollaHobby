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
    Button btnResult1;
    Button btnResult2;
    Button btnNext;
    ImageButton btnLogo;

    RadarChart radarChart;

    ArrayList<Integer> arr = new ArrayList<Integer>();
    HashMap<String, Integer> map = new HashMap<>();

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
        btnLogo = findViewById(R.id.btnLogo);

        radarChart=findViewById(R.id.mapsearchdetail_radar_chart);

        Intent intent=getIntent();

        final String dataName = intent.getStringExtra("Name");
        final int product= intent.getIntExtra("productiontype", -1);
        final int create= intent.getIntExtra("creationtype", -1);
        final int appreciation= intent.getIntExtra("appreciationtype", -1);
        final int entertain= intent.getIntExtra("entertainmenttype", -1);
        final int analy= intent.getIntExtra("analysistype", -1);

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

        Collections.sort(arr);//정렬

        if(arr.get(4)==arr.get(3)){//1등이 2개
            Toast.makeText(ResultActivity.this, "동점!그래프를 확인해보세요.", Toast.LENGTH_SHORT).show();

            String title= getKey(map, arr.get(4));

            btnResult1.setText(getKey(map, arr.get(4)));
            map.remove(getKey(map, arr.get(4)));

            btnResult2.setText(getKey(map, arr.get(3)));
            map.put(title, arr.get(4));
        }

        else if(arr.get(4)!=arr.get(3) && arr.get(3)==arr.get(2)){//1등 1개, 2등이 2개
            Toast.makeText(ResultActivity.this, "동점!그래프를 확인해보세요.", Toast.LENGTH_SHORT).show();
        }

        else{
            btnResult1.setText(getKey(map, arr.get(4)));
            btnResult2.setText(getKey(map, arr.get(3)));
        }

        dataValue();
        makeChart();

        BtnResultListener btnResultListener = new BtnResultListener();
        btnResult1.setOnClickListener(btnResultListener);
        btnResult2.setOnClickListener(btnResultListener);

        HomeBtnListener homeBtnListener = new HomeBtnListener();
        btnNext.setOnClickListener(homeBtnListener);
        btnLogo.setOnClickListener(homeBtnListener);


    }//end of onCreate

    class BtnResultListener implements View.OnClickListener{//Result버튼 이벤트
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btnResult1:
                    whatPage(btnResult1.getText().toString());
                    break;
                case R.id.btnResult2:
                    whatPage(btnResult2.getText().toString());
                    break;
            }
        }

        public void whatPage(String result){
            Intent intent;
            switch (result){
                case "분석형":
                    intent = new Intent(getApplicationContext(), HobbyActivity.class);
                    intent.putExtra("id", 4);
                    startActivity(intent);
                    break;
                case "오락형":
                    intent = new Intent(getApplicationContext(), HobbyActivity.class);
                    intent.putExtra("id", 3);
                    startActivity(intent);
                    break;
                case "제작형":
                    intent = new Intent(getApplicationContext(), HobbyActivity.class);
                    intent.putExtra("id", 0);
                    startActivity(intent);
                    break;
                case "감상형":
                    intent = new Intent(getApplicationContext(), HobbyActivity.class);
                    intent.putExtra("id", 2);
                    startActivity(intent);
                    break;
                case "창작형":
                    intent = new Intent(getApplicationContext(), HobbyActivity.class);
                    intent.putExtra("id", 1);
                    startActivity(intent);
                    break;
            }
        }
    }

    class HomeBtnListener implements View.OnClickListener{//SelectActivity로 넘어가
        @Override
        public void onClick(View v){
            Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
            startActivity(intent);
        }
    }

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

        dataVals.add(new RadarEntry(map.get("분석형")));
        dataVals.add(new RadarEntry(map.get("오락형")));
        dataVals.add(new RadarEntry(map.get("감상형")));
        dataVals.add(new RadarEntry(map.get("제작형")));
        dataVals.add(new RadarEntry(map.get("창작형")));

        return dataVals;
    }

    public void makeChart(){
        RadarDataSet dataSet= new RadarDataSet(dataValue(),"SCORE");
        dataSet.setColor(Color.RED);

        RadarData data = new RadarData();
        data.addDataSet(dataSet);
        String[] labels={"분석형", "오락형", "감상형", "제작형", "창작형"};
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.setData(data);
    }
}
