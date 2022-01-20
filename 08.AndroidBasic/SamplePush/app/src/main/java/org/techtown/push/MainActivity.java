package org.techtown.push;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        // 등록 ID 확인되었을때 값을 받는 리스너 등록
        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener(new OnCompleteListener<String>() {

                // 등록이 확인되면 onComplete 수행
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if(!task.isSuccessful()){
                        Log.d("Main", "토큰 정보를 가져오는데 실패함", task.getException());
                        return;
                    }

                    String newToken = task.getResult();
                    println("등록 ID : " + newToken);
                    Log.d("Main", "등록 ID"+newToken);
                }
            });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String instanceId = FirebaseInstallations.getInstance().getId().toString(); // 등록 id값 확인을 위한 메서드 호출
                // 인스턴스 id는 등록 id와 달라서 등록 id의 일부분만 확인 가능하다.

                println("확인된 인스턴스 id : " + instanceId);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        println("onNewIntent 호출됨");

        if(intent != null){
            processIntent(intent);
        }

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        String from = intent.getStringExtra("from");
        if(from == null){
            println("from is null.");
            return;
        }

        String contents = intent.getStringExtra("contents"); // 보낸 데이터는 키(Key)를 이용해 확인
        println("DATA : " + from + ", " + contents);
        textView.setText("[" + from + "]로부터 수신한 데이터 : " +contents);
    }

    public void println(String data){
        textView2.append(data + "\n");
    }

}