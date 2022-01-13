package org.techtown.sampleprogress;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Max 100으로 설정됨
        ProgressBar progressBar = findViewById(R.id.progressBar); // 프로그래스바 객체 참조하여 설정하기
        progressBar.setIndeterminate(false);
        progressBar.setProgress(80); // 현재 진행도 값은 80
        // ProgressBar.incrementProgressBy() 현재 설정된 값에서 빼거나 더할때 사용
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dialog = new ProgressDialog(MainActivity.this); // ProgressDialog는 파라미터로 화면에 표시할 컨텍스트가 필요.
                                                                       // 프로그래스 대화상자 객체 생성 후 설정
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("데이터를 확인하는 중입니다.");

                dialog.show();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(dialog != null){ // 프로그래스 대화상자 없애기 다이얼로그 영역 밖을 클릭하면 닫히지만 특정 이벤트로 닫히지 않는 경우 강제로 닫는다.
                    dialog.dismiss();
                }
            }
        });
    }
}