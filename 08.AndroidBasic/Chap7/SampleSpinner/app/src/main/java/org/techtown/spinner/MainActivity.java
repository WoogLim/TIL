package org.techtown.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    String[] items = {"mike","angle","corw","john","ginne","sally","cohen","rice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Spinner spinner = findViewById(R.id.spinner);
        // 스피너의 경우 기본으로 제공하는 어뎁터 API가 존재해서 리사이클러뷰와 같이 별도로 작성하지 않아도 된다.
        // 액티비티인 this를 전달, 뷰를 초기화할때 사용되는 XML 레이아웃 리소스 ID(문자열을 아이템으로 보여주는 단순 레이아웃), 아이템으로 보일 문자열
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items);
        // 스피너는 항목을 선택하는 별도의 창이 있어 다음과 같이 작동방식을 설정해야한다.
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        // 스피너에 어뎁터 설정 선택 위젯이므로 스피너 또한
        spinner.setAdapter(adapter);

        // 스피너 리스너 설정하기
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                textView.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("");
            }
        });
    }
}