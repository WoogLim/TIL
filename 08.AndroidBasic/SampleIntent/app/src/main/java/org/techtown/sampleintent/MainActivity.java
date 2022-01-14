package org.techtown.sampleintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101; // 응답값은 마음대로 정함 어떤 액티비로 응답이 온 것인지 구분하기 위함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class); // 인텐트는 주로 엑티비티 실행 및 값을 공유하기 위해 사용
                // 이벤트 처리 메소드onClick 이므로 this를 통해 컨텍스트 참조가 불가능하다. getApplicationContext()로 이 앱의 Context를 입력한다. 두번째 파라미터 로는 불러올 클래스로 지정한다.
                startActivityForResult(intent, REQUEST_CODE_MENU); // startActivity 메서드처럼 새 액티비티를 실행하지만 이 메소드는 새 액티비티로 부터 응답값을 받아온다.
            }
        });
    }

    // 엑티비티 결과를 불러오는 메서드 재정의 - 새로 띄운 액티비티가 응답을 보내면 호출됨
    /*
    * requestCode : 전달한 코드 101
    * resultCode : 새 액티비티로부터 전달된 응답 코드
    * data : 새 액티비티로부터 전달받은 인텐트 < 이 인텐트에 저장하는 방법은 새 액티비티에서 putExtra 사용 key와 value 형태로 넣음
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(),
                    "onActivityResult 메서드 호출됨. 요청 코드 : " + requestCode + ",결과 코드 : " + resultCode, Toast.LENGTH_LONG).show();
            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                Toast.makeText(getApplicationContext(),
                        "응답으로 전달된 name : " + name, Toast.LENGTH_LONG).show();
            }
        }
    }
}