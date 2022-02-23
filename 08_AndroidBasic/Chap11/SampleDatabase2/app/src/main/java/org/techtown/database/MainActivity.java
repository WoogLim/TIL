package org.techtown.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView;

    DatabaseHelper dbHelper;
    SQLiteDatabase database;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableName = editText2.getText().toString();
                createTable(tableName);

                insertRecord();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                executeQuery();
            }
        });
    }

    private void createDatabase(String name){
        println("createDatabase 호출됨.");

        dbHelper = new DatabaseHelper(this); // DatabaseHelper 객체 생성 후
        database = dbHelper.getWritableDatabase(); // SQLiteDatabase 참조 DB 생성시 getWritableDatabase 또는 getReadableDatabase 메서드를 호출해야 한다.
        // 데이터베이스를 만들기 위한 메서드 실행하기
        /* Params
        * name : 데이터베이스 명
        * MODE_PRIVATE : 모드
        * factory : 반환 결과 객체
        * */
//      database = openOrCreateDatabase(name, MODE_PRIVATE, null);

        println("데이터베이스 생성함: " + name);
    }

    private void createTable(String name) {
        println("createTable 호출됨.");

        if (database == null) {
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }

        database.execSQL("create table if not exists " + name + "("
                + "_id integer PRIMARY KEY autoincrement, "
                + "name text, "
                + "age integer, "
                + "mobile text)");

        println("테이블 생성함: " + name);
    }

    public void insertRecord(){
        println("insertRecord 호출됨.");
        if(database == null){
            println("데이터베이스를 먼저 생성하세요.");
            return;
        }

        database.execSQL("insert into " + tableName
            + "(name, age, mobile) "
            + "values "
            + "( 'John', 20, '010-1000-1000')"
        );
        println(" 레코드 추가함 ");
    }

    public void executeQuery() {
        println("executeQuery() 호출됨.");

        // executeSQL 과 다르게 raqQuery는 결과값을 반환한다.
        Cursor cursor = database.rawQuery("select _id, name, age, mobile from emp", null); // SQL 실행 후 cursor 객체 반환
        int recordCount = cursor.getCount(); // 조회 데이터 건수
        println("레코드 개수  : " + recordCount);

        for( int i = 0 ; i < recordCount ; i++ ){

            // 커서 객체는 처음부터 아무런 레코드를 가리키지 않음. moveToNext를 통해 그다음 레코드를 가리키도록 해야한다.
            // while 구문을 통해 moveToNext가 false값을 반환할 때까지 레코드를 가져오는 것을 수행하는것이 일반적임
            cursor.moveToNext(); // 다음 결과 레코드로 넘어가기 반환값이 boolean임.
            // 각행의 인덱스 컬럼을 각각 지정
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            println("레코드 #" + i + " : " + id + ", " + name + ", " + age + ", " + mobile);
        }
        cursor.close();
    }

    public void println(String data){
        textView.append(data + "\n");
    }
}