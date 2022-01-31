package org.techtown.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 엑티비티가 만들어지는 순간에 자동 호출됨
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // XML을 인플레이트해 메모리에 추가
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 액티비티가 만들어지고(화면이 띄어진 후 추가할 때)
    // 메뉴가 새로 보일 때마다 호출되므로 특정값에 따라 메뉴의 속성을 변경해 보이거나 안보이게 할 수 있다.
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return super.onPrepareOptionsMenu(menu);
//    }

    // 메뉴가 터치될 때 실행 메뉴 아이템의 id 값이 넘어감
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.menu_refresh:
                Toast.makeText(this, "새로고침 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "설정 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 컨텍스트 메뉴를 특정 뷰에 등록하고 싶을 때 사용. 컨텍스트 메뉴는 화면을 길게 터치하면 나타남.
//    @Override
//    public void registerForContextMenu(View view) {
//        super.registerForContextMenu(view);
//    }

    // 컨텍스트 메뉴가 선택된 경우 호출
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        .... = item.getItemId();
//        return super.onContextItemSelected(item);
//    }
}