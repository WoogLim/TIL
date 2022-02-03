package org.techtown.ninepatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // 나인패치란 img 파일 이름 뒤에 9을 붙여 가운데 폭만 늘어나도록해 깨짐 현상이 적게 나타나게 하는 효과이다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}