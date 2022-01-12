package org.techtown.sampleorientation2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // 매니페스트 해당 엑티비티에 android:configChanges="orientation|screenSize|keyboardHidden" 추가
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}