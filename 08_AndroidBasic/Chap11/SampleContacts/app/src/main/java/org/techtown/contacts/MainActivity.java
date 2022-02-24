package org.techtown.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                chooseContacts();
            }
        });
    }

    public void chooseContacts() {
        // 연락처 화면을 띄우기 위한 인텐트 생성
        // 첫번째 인자로 액션 정보, 두번째 인자로 연락처 정보를 조회하는데 사용하는 URI값을 넣어준다.
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactPickerIntent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == 101){
                try{
                    Uri contactsUri = data.getData();
                    String id = contactsUri.getLastPathSegment();

                    getContacts(id);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("Range")
    public void getContacts(String id){
        Cursor cursor = null;
        String name = "";

        try{
            // ContactsContract.Data.CONTENT_URI : 이 Uri 값은 앞에서 인텐트를 만들때 사용한 Uri와 다르다. 연락처의 상세 정보를 조회하는데 사용되는 Uri임.
            // ContactsContract.Data.CONTACT_ID : 이름 중 id 컬럼의 이름을 확인 ? 자리는 세번째 0번째 인덱스로 대체된다.
            cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                            null, ContactsContract.Data.CONTACT_ID + "=?",
                                    new String[] { id }, null);
            if(cursor.moveToFirst()) {
                // 이름 칼럼 상수를 인자로 넣어 인덱스 값을 가져오고 해당 인덱스의 이름값을 가져온다.
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                println("Name : " + name);

                // 현재 레코드의 모든 컬럼명을 조회한다.
                String columns[] = cursor.getColumnNames();
                for(String column : columns){
                    // 컬럼명을 인자로 넣어 해당 인덱스 값을 가져온다.
                    int index = cursor.getColumnIndex(column);
                    String columnOutput = ("#"+ index + " -> [" + column + "] " + cursor.getString(index));
                    println(columnOutput);
                }
                cursor.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void println(String data){
        textView.append(data + "\n");
    }
}