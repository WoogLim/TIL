package org.techtown.sampleparcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    TextView textView;

    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        Intent intent = getIntent(); // 객체를 반환
        processIntent(intent);

    }

    private void processIntent(Intent intent){
        if(intent != null){
            Bundle bundle = intent.getExtras(); // 객체안의 번들을 참조 Bundle 자료형의 객체가 반환됨 번들 객체를 참조하지 않아도 인텐트 객체의 정의되어 있는 .getParcelableExtra("KEY")를 사용해도 된다.
            SimpleData data = bundle.getParcelable(KEY_SIMPLE_DATA); // 번들(자바에서 사용했던 Object와 유사하다고 생각하면됨.) 객체안의 Simpledata 객체를 getParcelable 메서드로 참조한다. 메인에서 불려진 키값을 메뉴에서 사용한다.
            // Parcelable 인터페이스를 사용하면 객체를 정의해 전달이 가능하다.
            if(intent != null){
                textView.setText("전달 받은 데이터\n Number : " + data.number + "\n Message : " + data.message);
            }
        }
    }
}