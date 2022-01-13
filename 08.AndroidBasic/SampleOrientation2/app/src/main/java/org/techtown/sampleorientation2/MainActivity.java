package org.techtown.sampleorientation2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 화면에 보이는 레이아웃만 변경할 경우 엑티비티를 다시 만드는 과정은 필요없다. 아래 설정을 추가해 엑티비티를 다시 만드는 과정을 없앤다.
    // 매니페스트 해당 엑티비티에 android:configChanges="orientation|screenSize|keyboardHidden" 추가
    /*
    * 방향이 바뀔때마다 엑티비티에서 인식 바뀌는 시점에 configurationChanged 메서드가 자동으로 호출
    * keyboardHidden값은 단말의 방향 전환과는 상관없지만 액티비티가 보일 때 키패드가 자동으로 나타나지 않도록 하며
    * 보여야 할 시점을 액티비티에서
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // configurationChanged 재정의
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig); // |screenSize|keyboardHidden 정보 불러옴

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            // 가로 방향
            showToast("방향 : ORIENTATION_LANDSCAPE");
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            // 세로 방향
            showToast("방향 : ORIENTATION_PORTRAIT");
        }
    }

    public void showToast(String data){
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}