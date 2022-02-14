package org.techtown.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {
    // context 객체만 받음 안드로이드는 UI 생성시 Context 객체를 전달받게 되어 있으므로 context객체는 무조건 들어간다.
    public MyButton(Context context) {
        super(context);
        init(context);
    }

    // context 객체와 AttributeSet 객체를 받음 XML 에 속성을 추가하기위해 사용
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        // 초기화를 위한 메서드 정의
        
        // 배경색 지정
        setBackgroundColor(Color.CYAN); 
        // 글자색 지정
        setTextColor(Color.BLACK);

        float textSize = getResources().getDimension(R.dimen.text_size); // getDimension 값은 픽셀값으로 반환된 값이다.
        // setTextSize는 픽셀단위만 설정가능 sp 단위 설정하려면 XML 파일을 이용해야한다. value에 dimens.xml을 생성해 text_size값을 설정해준다.
        setTextSize(textSize);
    }

    // 뷰(위젯)가 그려질때 자동 호출됨
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("MyButton", "onDraw() 호출됨");
    }

    // 뷰(위젯)이 터치될 때 자동 호출됨
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyButton", "onTouchEvent() 호출됨");

        int action = event.getAction();
        switch(action){
            // 버튼을 누른 경우
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);

                break;

            // 누른 상태에서 땐 경우
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.CYAN);
                setTextColor(Color.BLACK);

                break;
        }

        // 화면을 다시 그리는 메서드 호출 이후에 onDraw가 수행됨
        invalidate();
        return true;
    }
}
