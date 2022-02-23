package org.techtown.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


// 내용 제공자 활용 예제. 우선 매니페스트에 내용 제공자 사용 관련 권한을 부여받는다.
public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                insertPerson();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                queryPerson();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletePerson();
            }
        });
    }

    public void insertPerson() {
        println("insertPerson 호출됨");

        String uriString = "content://org.techtown.provider/person";

        // Uri 객체 생성 문자열을 Uri 객체로 만들기 위해 Uri.Builder() 객체 생성. build와 parse메서드를 호출해 문자열을 파라미터로 전달.
        Uri uri = new Uri.Builder().build().parse(uriString);

        // 내용 제공자의 조회 쿼리 이용 query 메서드는 cursor 객체 반환
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        // 반환된 cursor 객체에서 결과레코드의 컬럼 이름 조회
        String[] columns = cursor.getColumnNames();

        println("columns count -> " + columns.length);
        for(int i = 0 ; i < columns.length ; i++){
            println("#" + i + " : " + columns[i]);
        }

        // 레코드 추가시 ContentValues 사용. getColumnNames 메서드를 이용해 알아낸 컬럼 이름을 사용할 수 있고 아래처럼 직접 컬럼 이름을 지정할 수도 있다.
        ContentValues values = new ContentValues();
        values.put("name", "john");
        values.put("age", 20);
        values.put("mobile", "010-1000-1000");

        // 내용제공자의 insert 메서드를 호출하여 레코드를 추가할 때에는 Uri와 함께 컬럼에 적용할 값을 넣어준다.
        uri = getContentResolver().insert(uri, values);
        println("insert 결과 -> " + uri.toString());
    }

    public void queryPerson(){
        try{
            String uriString = "content://org.techtown.provider/person";
            // Uri 객체 생성
            Uri uri = new Uri.Builder().build().parse(uriString);

            // 컬럼명 지정
            String[] columns = new String[] {"name", "age", "mobile"};
            // 내용 제공자의 query 메서드 이용 결과값을 커서 객체로 받아온다. 두번째 인자로 조회할 컬럼들을 지정
            Cursor cursor = getContentResolver().query(uri, columns, null, null, "name ASC");
            
            println("query 결과 : " + cursor.getCount());

            // 인덱스 별 쿼리 결과 조회함. cursor 는 -1 부터 시작 moveToNext로 다음 인덱스 조회
            int index = 0;
            while(cursor.moveToNext()){
                // 각 행의 컬럼명 값을 가져온다.
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                @SuppressLint("Range") String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));

                println("#" + index + " -> " + name + ", " + age + ", " + mobile);
                index += 1;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updatePerson(){
        String uriString = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = "mobile = ?"; // ? 는 selectionArgs의 첫번째 인덱스로 대체된다.
        String[] selectionArgs = new String[] {"010-1000-1000"};
        ContentValues updateValue = new ContentValues();
        updateValue.put("mobile", "010-2000-2000");

        // update 메서드는 변경된 레코드 수 반환 uri, 변경할 컬럼명, 값 외에 조건절과 조건값을 추가한다. 010-1000-1000 mobile 레코드를 변경
        int count = getContentResolver().update(uri, updateValue, selection, selectionArgs);

        println("update 결과 : " + count);
    }

    public void deletePerson(){
        String uriString = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);

        String selection = "name = ?";
        String[] selectionArgs = new String[] {"john"};

        // delete 메서드는 uri, 조건절, 조건 값 추가한다. name이 john인 레코드를 제거한다.
        int count = getContentResolver().delete(uri, selection, selectionArgs);
        println("delete 결과 : " + count);
    }

    public void println(String data){
        textView.append(data + "\n");
    }
}