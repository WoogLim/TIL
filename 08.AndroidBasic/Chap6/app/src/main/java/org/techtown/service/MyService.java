package org.techtown.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() 호출됨.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() 호출됨.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() 호출됨.");

        if(intent == null){
            return Service.START_STICKY; // 인텐트 객체가 null이 아니면 processCommand() 호출
                                         // START_STICKY는 서비스가 비정상종료되었다는 의미로 시스템이 자동으로 재시작함.
        } else{
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent){
        String command = intent.getStringExtra("command"); // 인텐트에서 부가 데이터 가져오기
        String name = intent.getStringExtra("name");

        Log.d(TAG, "command : " + command + ", name : " + name);

        for(int i = 0 ; i < 5 ; i++){
            try{
                Thread.sleep(1000);
            } catch (Exception e){
                Log.d(TAG, "Waiting" + i + " seconds.");
            }
        }

        Intent showIntent = new Intent(getApplicationContext(), MainActivity.class); // 액티비티를 띄우기 위한 인텐트 객체 만들기

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TOP); // 서비스는 화면이 없기 때문에 화면이 있는 액티비티를 띄우기 위해 NEW_TASK 그리고 MainActivity 객체가 이미 메모리에 있는경우
                                                             // 재사용 가능하도록 SINGLE_TOP, CLEAR_TOP 플래그도 인텐트에 추가한다.
        showIntent.putExtra("command", "show");
        showIntent.putExtra("name", name + " from service.");
        startActivity(showIntent); // 액티비티 -> 서비스 startService 서비스 -> 액티비티 startActivity 를 사용했다.
    }
}