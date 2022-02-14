package org.techtown.samplelayoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = findViewById(R.id.button); // 인플레이트(객체가 메모리에저장) 되지 않은 버튼 객체를 참조하면 에러가 발생해 앱이 실행되지 않는다.
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "버튼이 눌렸어요",Toast.LENGTH_LONG).show();
            }
        });

        setContentView(R.layout.activity_main);
        // XML은 앱이 실행하는 시점에 XML 레이아웃에 들어있는 뷰들을 인플레이트(메모리에 객체화해 저장)된다.
        // 화면 전체에 나타날 레이아웃을 정의하는 기능도 같이 있다.
        // 부분 화면을 메모리에 객체화하는 경우 LayoutInflater 를 사용한다.

        // 메인 레이아웃은 setContentView와 같은 방법으로 객체화해 메모리에 저장
        // 부분 레이아웃은 LayoutInflater를 이용해 객체화해 메모리에 저장
    }
}