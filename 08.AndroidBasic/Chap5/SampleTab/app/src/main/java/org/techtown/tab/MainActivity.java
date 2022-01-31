package org.techtown.tab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // 정의한 Toolbar 객체는 setSupportActionBar 메서드를 사용해 액션바로 설정해야 한다. themes.xml 에서 액션바를 없앤 후 실행한다.

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 메인 액티비티에 디폴트로 만들어진 액션바가 없는 경우 동작
                                                     // 하지만 액션바는 디폴트로 만들어지는데, 이것은 액션바가 포함된 테마를 이용하기 때문이다.
                                                     // 액션바를 포함하지 않으려면 values 폴더에 themes.xml을 수정해야한다.

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit(); // 프래그먼트 연결

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록")); // 0
        tabs.addTab(tabs.newTab().setText("스팸기록")); // 1
        tabs.addTab(tabs.newTab().setText("연락처")); // 2
        // 탭 메뉴 추가 addTab

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){ // 탭 버튼이 눌릴 때 호출됨
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                Fragment selected = null;
                if(position == 0){
                    selected = fragment1;
                }else if(position == 1){
                    selected = fragment2;
                }else if(position == 2){
                    selected = fragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}