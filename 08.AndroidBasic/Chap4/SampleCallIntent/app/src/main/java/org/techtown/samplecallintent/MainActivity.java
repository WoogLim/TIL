package org.techtown.samplecallintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 뷰 객체 참조

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString(); // 입력상자에 입력된 전화번호 확인

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data)); // 전화걸기 화면을 보여줄 인텐트 객체 생성
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName name = new ComponentName("org.techtown.samplecallintent",
                        "org.techtown.samplecallintent.MenuActivity"); // 컴포넌트 이름을 지정할 수 있는 객체 생성
                intent.setComponent(name); // 인텐트 객체에 컴포넌트 지정
                startActivityForResult(intent, 101); // 액티비티 띄우기
            }
        });
    }
}