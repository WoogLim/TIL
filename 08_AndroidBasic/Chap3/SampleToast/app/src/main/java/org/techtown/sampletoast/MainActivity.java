package org.techtown.sampletoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    // 토스트 메시지 모양 변경해 보여주기
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v){
        LayoutInflater inflater = getLayoutInflater(); // 인플레이터로 XML로 정의된 레이아웃(toastborder.xml)을 메모리에 객체화

        View layout = inflater.inflate( // 토스트를 위한 레이아웃 인플레이션
            R.layout.toastborder,
            (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);

        Toast toast = new Toast(this); // 토스트 객체 생성
        text.setText("모양 바꾼 텍스트");
        toast.setGravity(Gravity.CENTER, 0, -100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void onButton2Clicked(View v){
        Snackbar.make(v, "스낵바 입니다.", Snackbar.LENGTH_LONG).show();
    }
}