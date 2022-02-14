package org.techtown.sampleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button); // 버튼 객체 참조
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike"); // 인텐트 생성 후 name에 부가 데이터로 mike 저장
                // Parameter 응답코드, 인텐트
                setResult(RESULT_OK, intent); // 응답 보내기 인텐트 객체가 파라미터로 보내짐 주로 새로띄운 엑티비티에서
                                              // 이전 엑티비티로 값(응답코드와 인텐트)을 보낼때 사용
                finish(); // 현재 엑티비티 없애기
            }
        });
    }
}