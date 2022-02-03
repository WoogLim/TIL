package org.techtown.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Layout1 layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout1 layout1 = findViewById(R.id.layout1); // XML 레이아웃에 추가한 뷰 참조하기
        layout1 = findViewById(R.id.layout1);
        layout1.setImage(R.drawable.img); // 뷰의 메서드 호출하여 데이터 설정하기
        layout1.setName("임건");
        layout1.setMobile("010-4912-4823");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                layout1.setImage(R.drawable.img_1);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                layout1.setImage(R.drawable.img);
            }
        });
    }
}