package org.techtown.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "employee.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, NAME, null, VERSION);
    }

    // 데이터 베이스가 생성될 때 호출
    public void onCreate(SQLiteDatabase db) {
        println("onCreate 호출됨.");

        String sql = " create table if not exists emp( "
                    + " _id integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " age integer, "
                    + " mobile text) ";

        db.execSQL(sql);
    }

    // 데이터 베이스를 열 때 호출
    public void onOpen(SQLiteDatabase db){
        println("onOpen 호출됨.");
    }

    // SQLiteDatabase 파일의 버전과 다를 경우에 자동으로 호출되는 onUpgrade 메서드에는 SQLiteDatabase 객체와 함께 기존 버전 정보를 담고 있는 old-
    // Version, 현재 정보를 담고 있는 newVersion 파라미터가 전달됩니다.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        println("onUpgrade 호출됨: " + oldVersion + " -> " + newVersion);

        if(newVersion > 1){
            db.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    public void println(String data){
        Log.d("DatabaseHelper", data);
    }
}
