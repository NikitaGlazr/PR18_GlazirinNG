package com.example.pr18_glazirinng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivityN4 extends AppCompatActivity {
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";
    ListView lvSimple;
    Button btnNext, btnPrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_n4);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext.setOnClickListener(v -> {next();});
        btnPrev.setOnClickListener(v -> {prev();});

// массив данных
        int load[] = { 10, 20, 30, 40, 50, 60,70, 80, 90, 100};

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                load.length);
        Map<String, Object> m;
        for (int i = 0; i < load.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i+1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB,
                ATTRIBUTE_NAME_LL };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvLoad, R.id.pbLoad, R.id.llLoad };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item_n4,
                from, to);
        // Указываем адаптеру свой биндер
        sAdapter.setViewBinder(new MyViewBinder());

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }

    public void next(){
        Intent intent = new Intent(this, MainActivityN5.class);
        startActivity(intent);
    }

    public void prev(){
        Intent intent = new Intent(this, MainActivityN3.class);
        startActivity(intent);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i = 0;
            if(view.getId() == R.id.llLoad){
                i = ((Integer) data).intValue();
                if (i < 40) view.setBackgroundColor(green); else
                if (i < 70) view.setBackgroundColor(orange); else
                    view.setBackgroundColor(red);
                return true;
            }
            if(view.getId() == R.id.pbLoad){
                i = ((Integer) data).intValue();
                ((ProgressBar)view).setProgress(i);
                return true;
            }
            return false;
        }
    }
}
