package com.example.pr18_glazirinng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivityN3 extends AppCompatActivity {
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    // картинки для отображения динамики
    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;
    Button btnPrev, btnNext;
    ListView lvSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_n3);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext.setOnClickListener(v -> {next();});
        btnPrev.setOnClickListener(v -> {prev();});
        // массив данных
        int[] values = { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 };

        // упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                values.length);
        Map<String, Object> m;
        int img = 0;
        for (int i = 0; i < values.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1));
            m.put(ATTRIBUTE_NAME_VALUE, values[i]);
            if (values[i] == 0) img = 0; else
                img = (values[i] > 0) ? positive : negative;
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE,
                ATTRIBUTE_NAME_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.tvText, R.id.tvValue, R.id.ivImg };

        // создаем адаптер
        MySimpleAdapter sAdapter = new MySimpleAdapter(this, data,
                R.layout.item_n3, from, to);

        // определяем список и присваиваем ему адаптер
        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);

    }

    public void next(){
        Intent intent = new Intent(this, MainActivityN4.class);
        startActivity(intent);
    }

    public void prev(){
        Intent intent = new Intent(this, MainActivityN2.class);
        startActivity(intent);
    }

    class MySimpleAdapter extends SimpleAdapter {
        public MySimpleAdapter(Context context,
                               List<? extends Map<String, ?>> data, int resource,
                               String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public void setViewText(TextView v, String text) {
            // метод супер-класса, который вставляет текст
            super.setViewText(v, text);
            // если нужный нам TextView, то разрисовываем
            if (v.getId() == R.id.tvValue) {
                int i = Integer.parseInt(text);
                if (i < 0) v.setTextColor(Color.RED); else
                if (i > 0) v.setTextColor(Color.GREEN);
            }
        }

        @Override
        public void setViewImage(ImageView v, int value) {
            // метод супер-класса
            super.setViewImage(v, value);
            // разрисовываем ImageView
            if (value == negative) v.setBackgroundColor(Color.RED); else
            if (value == positive) v.setBackgroundColor(Color.GREEN);
        }
    }
}
