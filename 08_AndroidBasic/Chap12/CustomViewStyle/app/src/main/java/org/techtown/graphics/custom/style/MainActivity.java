package org.techtown.graphics.custom.style;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomViewStyle customViewStyle = new CustomViewStyle(this);
        setContentView(customViewStyle);
    }
}