package org.techtown.sampledialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                showMessage();
            }
        });


    }

    private void showMessage(){
        // AlertDialog 기본 API 중 appcompat 패키지에 포함된 API도 존재한다. 이 API는 예전 OS에도 연동되도록 제공한다.
        // 대화 상자의 모양을 변경하고 싶은 경우 ex) 대화상자 안에 에디트텍스트가 들어가야 하는 경우.
        // 프래그먼트를 이용해 원하는 형태의 대화상자 화면을 생성할 수 있다. 프래그먼트 선행 학습 필요.
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // 대화상자를 만들기 위한 빌더 객체 생성
        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert); // android.R.drawable 은 안드로이드 기본 API에 포함된 아이콘

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() { // 예 버튼 추가와 OnClick 리스너 지정
            public void onClick(DialogInterface dialog, int which) {
                String message = "예 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() { // 취소 버튼 추가와 OnClick 리스너 지정
            public void onClick(DialogInterface dialog, int which) {
                String message = "취소 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() { // 아니오 버튼 추가와 OnClick 리스너 지정
            public void onClick(DialogInterface dialog, int which) {
                String message = "아니오 버튼이 눌렸습니다.";
                textView.setText(message);
            }
        });

        AlertDialog dialog = builder.create(); // 대화 객체 생성 후 보여주기
        dialog.show();
    }
}