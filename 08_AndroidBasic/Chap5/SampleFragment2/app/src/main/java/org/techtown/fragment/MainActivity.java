package org.techtown.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // getSupportFragmentManager는 더 많은 os 버전을 지원한다.
        // getFragmentManager와 같음
        // 메인프래그먼트는 액티비티를 위한 activity_main.xml파일에 추가되어 있어 id로 참조한다.
        // 프래그먼트는 뷰가 아니여서 Activity클래스에 있는 findViewById로 검색할 수 없다. findFragmentById를 통해 검색한다.
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        // 메뉴 프래그먼트는 새로운 객체로 만들어 변수에 할당한다.
        menuFragment = new MenuFragment(); // 객체를 만들더라도 액티비티 위에 연결을해야 프래그먼트가 동작한다.
    }

    public void onFragmentChanged(int index){
        if(index == 0){
            // replace의 첫 번째 파라미터는 프래그먼트를 담고 있는 레이아웃의 id가 되어야 한다.
            // 트랜잭션을 통해 오류가 생기면 다시 원래 상태로 돌릴 수 있어야 하므로 트랜잭션 객체를 만들어 실행한다.
            getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
        }
        else if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        }
    }
}