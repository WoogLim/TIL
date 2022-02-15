package org.techtown.threadanim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // 표준 자바에서 게임 등에 사용하는 대부분의 애니메이션 효과는 스레드를 이용해 구현한다.

    ImageView imageView;

    ArrayList<Drawable> drawableList = new ArrayList<Drawable>();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        // getDrawable == BitmapFactory.decodeResource
        drawableList.add(res.getDrawable(R.drawable.img));
        drawableList.add(res.getDrawable(R.drawable.img_1));
        drawableList.add(res.getDrawable(R.drawable.img_2));
        drawableList.add(res.getDrawable(R.drawable.img_3));
        drawableList.add(res.getDrawable(R.drawable.img_4));

        imageView = findViewById(R.id.imageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 애니메이션을 위한 스레드 객체 만들어 실행
                AnimThread thread = new AnimThread();
                thread.start();
            }
        });
    }

    class AnimThread extends Thread{
        @Override
        public void run() {
            int index = 0;
            for(int i = 0; i < 100 ; i++){
                final Drawable drawable = drawableList.get(index);
                index += 1;
                if(index > 4){
                    index = 0;
                }
                // 화면에 이미지를 보여주기 위해 핸들러의 post 메서드 수행
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}