package org.techtown.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String name = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MyService.class); // 인텐트 객체 생성하고 부가 데이터 넣기
                intent.putExtra("command", "show");
                intent.putExtra("name", name);

                startService(intent); // 서비스 시작하기 MyService 클래스의 onStartCommand 메서드로 전달된다.
            }
        });

        // 액티비티가 새로 만들어질 때 전달된 인텐트 처리하기 NEW_TASK
        Intent passedIntent = getIntent();
        processIntent(passedIntent);
    }

    // 액티비티가 이미 만들어져 있을 때 전달된 인텐트 처리하기 메모리에 이미 있는 상태 SINGLE_TOP, CLEAR_TOP
    @Override
    protected void onNewIntent(Intent intent) {

        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent != null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "command : " + command + ", name : " + name, Toast.LENGTH_SHORT).show();
        }
    }
}