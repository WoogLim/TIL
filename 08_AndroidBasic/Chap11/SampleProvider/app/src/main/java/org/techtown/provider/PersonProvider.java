package org.techtown.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.Nullable;

public class PersonProvider extends ContentProvider {

    // 내용 제공자 ContentProvider 를 만들기 위해서는 고유 값을 가진 content URI를 만들어야 한다. 이 예제에서는 앱의 패키지 이름과 person 테이블을 합쳐
    // content://org.techtown.provider/person/1 로 정한다.
    /* content://org.techtown.provider/person/1
    *  1. content:// -> 내용 제공자에 의해 제어되는 데이터라는 의미로. 항상 content:// 로 시작한다.
    *  2. Authority -> org.techtown.provider 부분을 가리키며 특정 내용 제공자를 구분하는 고유한 값
    *  3. BasePath -> person 부분을 가리키며 요청할 데이터의 자료형을 결정(여기에서는 테이블 이름)
    *  4. ID -> 맨 뒤에 1과 같은 숫자를 가리키며 요청할 데이터 레코드 저장
    * */
    private static final String AUTHORITY = "org.techtown.provider";
    private static final String BASE_PATH = "person";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final int PERSONS = 1;
    private static final int PERSON_ID = 2;

    // UriMatcher -> URI를 매칭하는데 사용
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // addURI 로 사용할 URI 추가
        uriMatcher.addURI(AUTHORITY, BASE_PATH, PERSONS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "#", PERSON_ID);
    }

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase(); // DB 생성

        return true;
    }

    // CREATE 내용제공자를 이용해 값을 추가하고 싶을 때 사용
    /*  insert Params
     *   Uri : URI
     *   ContentValues : 저장할 컬럼명과 값들이 들이간 ContentValues 객체
     *
     *   return 값 : 새로 추가된 값의 Uri 정보
     * */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long id = database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

        if(id > 0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, id);
            // notifyChange 메서드는 레코드를 추가, 수정, 삭제되었을때 변경이 일어났음을 알려주는 메서드이다.
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("추가 실패 -> URI :" + uri);
    }

    // MIME 타입을 알고싶을때 이용
    /*  getType Params
    *   Uri : 결과 값으로 MIME타입이 반환
    *
    *   return : MIME 타입
    * */
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case PERSONS:
                return "vnd.android.cursor.dir/persons";
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }
    }

    // READ 내용제공자를 이용해 값을 조회할 때 사용
    /*  query Params
    *   Uri : URI
    *   String[] projection : 어떤 컬럼들을 조회할 것인지 지정 null 값인 경우 모두 조회
    *   String selection :  SQL 에서 where 절에 들어올 조건을 지정 null 인 경우 where 절이 없음.
    *   Strings[] selectionArgs : 세 번째 파라머터 값이 있을 경우 안에 들어간 조건 값을 대체하기 위해 사용
    *   String sortOrder : 정렬 칼럼을 지정. null 인 경우 정렬이 적용되지 않음. groupBy 절
    *   Having 절
    *   orderBy 절
    * */
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        // match 메서드 호출 시 UriMatcher에 addURI 메서드를 이용해 추가된 URI 중에서 실행 가능한 것이 있는지 확인함.
        switch (uriMatcher.match(uri)){
            case PERSONS:
                cursor = database.query(DatabaseHelper.TABLE_NAME,
                                DatabaseHelper.ALL_COLUMNS,
                                s, null, null, null, DatabaseHelper.PERSON_NAME + " ASC");
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }
        // getContentResolver : 내용접근자에 접근하기 위해 사용 액티비티에서 getContentResolver 호출시 ContentResolver객체를 반환 이 객체에는 query, insert, update, delete
        // 등의 메서드가 정의되어 있어 내용 제공자의 URI를 파라미터로 전달하면서 데이터를 CRUD할 수 있다.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    // UPDATE
    /*  update Params
    *   Uri
    *   ContentValues : 저장할 컬럼명과 값들이 들이간 ContentValues 객체 null 이면 안된다.
    *   Where : where 절에 들어갈 조건을 지정 null 값이라면 where 절이 없음.
    *   OR , AND 세번째 파라미터로 값이 있는 경우 그 안에 들어갈 조건 값을 대체하기 위해 사용
    *
    *   return : 업데이트가 된 레코드의 개수
    * */
    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.update(DatabaseHelper.TABLE_NAME, contentValues, s, strings);
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }

    // DELETE 내용 제공자를 이용해 값을 삭제할 때 사용
    /*  delete Params
    *   Uri
    *   Where : where 절에 들어갈 조건을 지정 null 값이라면 where 절이 없음.
    *   OR , AND 두번째 파라미터로 값이 있는 경우 그 안에 들어갈 조건 값을 대체하기 위해 사용
    *
    *   return : 영향을 받은 레코드의 개수
    * */
    @Override
    public int delete(Uri uri, String s, String[] strings) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.delete(DatabaseHelper.TABLE_NAME, s, strings);
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return count;
    }
}