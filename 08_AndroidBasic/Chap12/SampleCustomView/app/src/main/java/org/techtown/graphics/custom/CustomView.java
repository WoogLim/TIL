package org.techtown.graphics.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

// 뷰에 그래픽 그리기 1. View 상속 받아 생성자 2개 생성
public class CustomView extends View {

    private Paint paint;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        // 2. 페인트 객체 초기화
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    // 3. onDraw 메서드 구현 : onDraw 는 뷰가 화면에 그려질 때 자동 호출된다.
    // 이 메서드에서 그래픽을 그리면 된다.
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 사각형 모양으로 좌표값을 설정하고 페인트 객체로 설정값을 넣어준다.
        canvas.drawRect(100, 100, 200, 200, paint);
    }

    // 4. 터치이벤트 재정의
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Toast.makeText(super.getContext(), "MotionEvent.ACTION_DOWN : " + event.getX() + ", " + event.getY(), Toast.LENGTH_SHORT).show();
        }

        return super.onTouchEvent(event);
    }
}
