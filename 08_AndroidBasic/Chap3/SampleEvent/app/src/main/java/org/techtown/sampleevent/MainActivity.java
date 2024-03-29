package org.techtown.sampleevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector; // 제스처 디텍터 객체
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        View view = findViewById(R.id.view);
        
        // 터치 이벤트
        view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                // motionEvent 에는 터치 좌표 및 액션 정보가 저장됨
                int action = motionEvent.getAction(); // 액션정보

                float curX = motionEvent.getX(); // 좌표 정보
                float curY = motionEvent.getY();

                if(action == MotionEvent.ACTION_DOWN){ // 손가락이 눌림
                    println("손가락 눌림 : " + curX + ", " + curY);
                } else if(action == MotionEvent.ACTION_MOVE){ // 손가락이 눌린 상태로 움직임
                    println("손가락 움직임 : " + curX + ", " + curY);
                }else if(action == MotionEvent.ACTION_UP){ // 손가락이 떼졌을 때
                    println("손가락 뗌 : " + curX +", "+curX);
                }

                return true;

            }

        });

        // GestureDetector : 터치 이벤트 중에서 스크롤과 같이 일정 패턴으로 구분되는 이벤트 객체
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener(){
            @Override
            public boolean onDown(MotionEvent motionEvent){
                println("onDown() 호출됨."); // 화면이 눌림
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent){
                println("onShowPress() 호출됨"); // 화면이 눌렀다 떼짐
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent){
                println("onSingleTapUp() 호출됨"); // 화면이 한 손가락으로 눌렸다 떼지는 경우

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1){
                println(("onScroll 호출됨 : " + v + ", "+ v1)); // 화면이 눌린 채 일정한 속도와 방향으로 움직였다 떼는 경우

                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent){
                println("onLongPress() 호출됨."); // 화면을 손가락으로 오래 누르는 경우
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1){
                println("onFling() 호출됨 : " + v + ", " + v1); // 화면이 눌린 채 가속도를 붙여 손가락을 움직였다 떼는 경우

                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener(){ // OnTouchListener 객체로 설정했기 때문에 자동으로 on Touch 실행
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                detector.onTouchEvent(motionEvent); // GestureDetector 객체의 onTouchEvent메서드를 호출
                                                    // MotionEvent 객체를 전달한다.
                                                    // GestureDetector 객체가 터치 이벤트 처리 후 GestureDetecotr 객체에 정의된
                                                    // 메서드를 호출한다.
                return true;
            }
        });

    }
    public void println(String data){
        textView.append(data + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "시스템 [BACK] 버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();

            return true;
        }

        return true;
    }
}