package org.techtown.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 리 싸이클러뷰에 레이아웃 매니저 설정하기
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // 격자로 표시하기 위해 GridLayoutManager를 레이아웃 매니저로 설정하기 두번째 파라미터는 컬럼의 갯수이다. 카드가 한행에 두개의 컬럼으로 배치된다.
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("김민수", "010-1000-1000"));
        adapter.addItem(new Person("김하늘", "010-2000-2000"));
        adapter.addItem(new Person("홍길동", "010-3000-3000"));
        adapter.addItem(new Person("내이름1", "010-4000-4000"));
        adapter.addItem(new Person("내이름2", "010-4000-4000"));
        adapter.addItem(new Person("내이름3", "010-4000-4000"));
        adapter.addItem(new Person("내이름4", "010-4000-4000"));
        adapter.addItem(new Person("내이름5", "010-4000-4000"));
        adapter.addItem(new Person("내이름6", "010-4000-4000"));
        adapter.addItem(new Person("내이름7", "010-4000-4000"));
        adapter.addItem(new Person("내이름8", "010-4000-4000"));
        adapter.addItem(new Person("내이름9", "010-4000-4000"));
        adapter.addItem(new Person("내이름10", "010-4000-4000"));

        // 리싸이클러뷰에 어뎁터 설정하기
        recyclerView.setAdapter(adapter);

        // 어뎁터에 리스너 설정하기 OnPersonItemClickListener 로 리스너 설정함
        // 이 리스너의 onItemClick 메서드가 호출되고 onItemClick 메서드 안에서 어댑터 객체의 getItem 메서드를 이용해 클릭된 아이템 객체를 확인한다.
        adapter.setOnClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItems(position); // 아이템 클릭 시 어뎁터에서 해당 아이템의 Person객체 가져오기
                Toast.makeText(getApplicationContext(), "아이템 선택됨: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}