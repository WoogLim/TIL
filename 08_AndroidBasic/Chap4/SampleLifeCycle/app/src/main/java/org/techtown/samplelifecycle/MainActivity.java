package org.techtown.samplelifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        nameInput = findViewById(R.id.nameInput);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        println("onCreate 호출됨");
    }

    // 앱을 종료하거나 비정상 종료되면 onPause, 앱이 실행되면 onResume() 이벤트는 항상 호출된다.
    // 주로 상태정보를 저장하는 경우 onPause에. 불러오는 경우 onResume에서 불러온다.

    @Override
    protected void onPause() {
        super.onPause();
        println("onPause 호출됨");
        saveState();
        Toast.makeText(this, "onPause 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        println("onStop 호출됨");
        Toast.makeText(this, "onStop 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        println("onDestroy 호출됨");
        Toast.makeText(this, "onDestroy 호출됨", Toast.LENGTH_SHORT).show();
    }
    // 메뉴 화면 띄울때 MainActivity가 가려지기 때문에 Destroy는 실행되지 않는다.

    @Override
    protected void onStart() {
        super.onStart();
        println("onStart 호출됨");
        Toast.makeText(this, "onStart 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        println("onResume 호출됨");
        restoreState();
        Toast.makeText(this, "onResume 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(this, "onSaveInstanceState 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(this, "savedInstanceState 호출됨", Toast.LENGTH_SHORT).show();
    }

    public void println(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        Log.d("Main", data);
    }

    protected void restoreState(){
//        SharedPreferences : 앱 내부 저장소에 상태정보용 파일을 저장한다. getSharedPreferences의 첫번째 인자는 저장소의 이름
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if((pref != null) && (pref.contains("name"))){
            String name = pref.getString("name", ""); // 없으면 공백
            nameInput.setText(name);
            println("restoreState 호출됨");
        }
    }

    protected void saveState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit(); // 데이터를 저장할 수 있도록 에딧 메서드 제공
        editor.putString("name", nameInput.getText().toString()); // 입력정보 저장
        println("saveState 호출됨");
        editor.commit(); // 적용
    }

    protected void clearState(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear(); // 초기화
        editor.commit();
    }
}