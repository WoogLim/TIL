package org.techtown.samplelayoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        container = findViewById(R.id.container);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                /*
                 * View inflate(XML레이아웃 리소스, 부모 컨테이너)
                 * */
                inflater.inflate(R.layout.sub1, container, true); // container를 id로 갖는 리니어 레이아웃 전체에 sub1.xml파일의 레이아웃을 설정
                                                                  // 이 과정에서 부분 레이아웃(sub1.xml)에 정의된 뷰들이 메모리에 로딩되며 객체화 과정을 거침
                                                                  // container에 객체화 되었으므로 container.findViewById로 참조해야한다.
                CheckBox checkBox = container.findViewById(R.id.checkBox);
                checkBox.setText("로딩되었어요");
            }
        });
    }
}