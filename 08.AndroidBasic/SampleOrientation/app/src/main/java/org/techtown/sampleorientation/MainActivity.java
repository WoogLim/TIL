package org.techtown.sampleorientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name;
    EditText editText;

    // layout 세로 layout-land 가로
    @Override
    protected void onCreate(Bundle savedInstanceState) { // savedInstanceState : Destroy 후 소멸된 정보들이 저장된다.
        super.onCreate(savedInstanceState); // savedInstanceState : Destroy 후 소멸된 정보들을 복구한다.
        setContentView(R.layout.activity_main);
        showToast("onCreate 호출됨");

        editText = findViewById(R.id.editText);

        Button button  = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                name = editText.getText().toString(); // 버튼 클릭시 사용자가 입력한 값을 name 변수에 할당
                showToast("입력된 값을 변수에 저장했습니다:" + name );
            }
        });

        if(savedInstanceState != null){
            // 화면이 초기화될 때 name 변수의 값 복원
            name = savedInstanceState.getString("name");
            showToast("값을 복원했습니다:" + name);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy 호출됨");
    }

    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }

    // 버튼 클릭시 값 저장
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("name", name); // name변수의 값 저장
    }
}