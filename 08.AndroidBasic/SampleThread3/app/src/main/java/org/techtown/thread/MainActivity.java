package org.techtown.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/* UI 를 처리하는 스레드를 메인 스레드라 부른다. 메인 스레드에서 이미 UI에 접근하고 있으므로
  새로 생성한 스레드에서는 핸들러 객체를 이용해서 메시지를 전달함으로서 메인스레드에서 처리하도록 만들 수 있다.

  다음은 안드로이드에서 제공하는 두가지 시나리오다.
  지연시간이 길어질 수 있는 앱이라면 오랜 시간 작업을 수행하는 코드를 별도로 분리한 다음 UI에 응답을 보내는 방식으로 개발한다.

  1. 서비스 사용하기 : 백그라운드 작업은 서비스로 실행하고 사용자에게는 알림 서비스로 알려준다. 만약 엑티비티로 결과 값을
  전달하고 이를 이용해 다른 작업을 수행한다면 브로드캐스팅으로 값을 전달한다.
  2. 스레드 사용하기 : 스레드는 같은 프로세스 안에서 동작하므로 작업 수행의 결과를 바로 처리 가능하다. 그러나 UI 객체는
  직접 접근할 수 없으므로 핸들러를 사용한다.
  */
public class MainActivity extends AppCompatActivity {
    TextView textView;

    MainHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        handler = new MainHandler();
    }

    // 기본 접근 같은 클래스, 패키지 이용
    class BackgroundThread extends Thread{
        int value = 0;

        public void run() {
            for(int i = 0 ; i < 100 ; i++){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }
                value += 1;
                Log.d("Thread", "value : " + value);
                // 메인스레드가 아닌곳에서 UI 객체에 접근할 수 없다. 에러코드는 안뜨지만 버튼을 터치하는 순간 에러가 발생한다.'
                // 핸들러를 이용해야한다.
                // textView.setText("value 값 : " + value);

                // 핸들러가 관리하는 메시지 큐에서 처리할 수 있는 메시지 객체를 반환받는다.
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);

                // 셋팅한 메시지 객체를 핸들러로 보낸다.
                handler.sendMessage(message);
            }
        }
    }

    /* 앱을 실행할 때 프로세스가 만들어지면 그와 함께 메인 스레드도 생성된다. 최상위에서 관리되는 앱 구성 요소인 액티비티, 브로드캐스트 수신자 등
    새로 만들어지는 윈도우를 관리하기 위한 메시지 큐를 실행한다. 메시지큐로 순차적으로 코드를 진행하는데, 메인스레드에서 이 처리할 메시지를 전달하는 역할을 핸들러가 수행한다.
    실행하려는 특정 기능이 있을때 핸들러가 포함된 스레드에서 순차적으로 실행시킬때 사용된다. 핸들러를 이용해 미래에 어떤 시점에 이용되도록 스케줄링도 가능하다. */

    /*
     * 핸들러가 관리하는 메시지 큐에서 처리할 수 있는 메시지 객체 하나를 참조하기 위해 obtainMessage 메서드를 이용하고 호출의 결과로 메시지 객체를 반환받는다.
     * 이 메시지 객체에 필요한 정보를 넣은 후 sendMessage 메서드를 이용해 메시지 큐에 넣을 수 있다. 메시지 큐에 들어간 메시지는 순서대로 핸들러가 처리하게 되며
     * 이때 handleMessage 메서드에 정의한 기능이 수행된다. 이때 handleMessage에 들어 있는 코드가 수행되는 위치는 새로 만든 스레드가 아닌 메인 스레드이다.
     * */
    class MainHandler extends Handler {

        // 핸들러 안에서 전달받은 메시지 객체 처리하기
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("value 값 : " + value);
        }
    }
}