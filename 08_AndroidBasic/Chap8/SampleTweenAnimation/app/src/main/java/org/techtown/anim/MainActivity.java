package org.techtown.anim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 리소스로 정의한 애니메이션 정의
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);

                // 뷰의 애니메이션 시작
                // 뷰 객체는 사용자가 선택한 뷰 즉. 버튼이 2배로 커진다.
                // 애니메이션 작동 후 돌아가는 화면이 이상하다. 반대로 애니메이션을 주도록 한다.
                view.startAnimation(anim);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 리소스로 정의한 애니메이션 정의
//                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale2);

                view.startAnimation(anim);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 리소스로 정의한 애니메이션 정의
//                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);

                view.startAnimation(anim);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 리소스로 정의한 애니메이션 정의
//                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);

                view.startAnimation(anim);
            }
        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 리소스로 정의한 애니메이션 정의
//                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);

                view.startAnimation(anim);
            }
        });

        // 이외에도 속도를 지속적으로 빠르게/느리게 하거나 튀도록 효과를 주기도하고
        // onWindowFocusChanged 등으로 사용자에게 보여질 시점에 애니메이션을 등록할 수 있다.
        // 또한 animationListener를 통해 onAnimationStart(Animation animation) 등 시작/종료/반복 애니메이션 작동 주기로 특정 동작을 지정할 수 있다.
    }
}