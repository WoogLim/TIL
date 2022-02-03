package org.techtown.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {
    ImageView imageView;
    TextView textView;
    TextView textView2;

    public Layout1(Context context) {
        super(context);
        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        // LayoutInflater 객체는 시스템 서비스로 제공되는 것으로 getSystemService메서드를 호출하면서 파라미터로 Context.LAYOUT_INFLATER_SERVICE
        // 상수를 전달하면 객체가 반환된다. 이객체의 inflate 메서드를 호출하면서 XML 레이아웃 파일을 파라미터로 전달하면 인플레이션이 진행되어 이 소스 파일과 매칭된다.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 인플레이션 진행
        inflater.inflate(R.layout.layout1, this, true);
        // 이후 layout1의 이미지뷰, 텍스트뷰를 참조할 수 있다.

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    // 뷰에 데이터 설정하기
    // 리소스 id의 경우 @drawable/house 라는 리소스 id로 참조하는데 이 id 값은 내부적으로 정수 값으로 표현된다.
    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setMobile(String mobile){
        textView2.setText(mobile);
    }
}
