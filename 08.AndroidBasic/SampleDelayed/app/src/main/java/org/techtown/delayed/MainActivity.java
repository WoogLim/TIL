package org.techtown.delayed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 핸들러에서 지연시간 발생시켜 미래 어느 순간에 처리하도록 설정하기
    // 핸들러의 경우 메시지큐에 쌓인 순서대로 순차적으로 수행되기 때문에 UI 객체에 영향을 주지 않으면서 지연 시간을 두고 실행한다.
    // 때문에 응답이 오지 않아 대기 상태에 빠지는것을 방지할 수 있다.

    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        String title = "원격 요청";
        String message = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "예";
        String titleButtonNo = "아니오";
        AlertDialog dialog = makeRequestDialog(title, message, titleButtonYes, titleButtonNo);
        // 설정된 다이얼로그 객체를 받아와 화면에 보여줌.
        dialog.show();

        textView.setText("대화상자 표시중...");
    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence message, CharSequence titleButtonYes, CharSequence titleButtonNo) {
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textView.setText("5초 후에 결과 표시됨.");

                // post 로 메시지큐로 보내면서 순서대로 처리되며 내부의 코드가 설정 지연시간 이후에 실행되도록 한다.
                // sendMessage 로 사용하는 경우 sendMessageAtTime/Delayed(Message(메시지), long(시간)) 를 사용한다.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("요청 완료됨");
                    }
                }, 5000);
            }
        });

        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return requestDialog.create();
    }
}