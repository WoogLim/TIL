package org.techtown.graphics.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 5. 메인엑티비티에 CustomView 클래스 추가
        CustomView view = new CustomView(this);
        setContentView(view);
    }
}