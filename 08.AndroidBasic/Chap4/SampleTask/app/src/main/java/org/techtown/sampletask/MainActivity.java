package org.techtown.sampletask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                // 첫 화면이 겹겹이 쌓이게 되며 백버튼 터치시 이전 첫 화면이 나온다.
                // 태스크는 앞에서 새로뜬 화면을 스택에 넣어 관리한다.

                // Manifest.xml 안에 launchmode를 singletop으로 두면 가장 위쪽의 있는 엑티비티는 더 이상 만들지 않게 된다.
                // intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); 과 같음.

                // 앱 실행 후 버튼을 아무리 눌러도 백버튼 한번 누르면 홈화면으로 빠져나간다.
                // MainActivity에서

                // Manifest.xml 안에 launchmode를 singletask로 두면 실행되는 순간 새로운 태스크를 생성한다.
            }
        });
    }
}