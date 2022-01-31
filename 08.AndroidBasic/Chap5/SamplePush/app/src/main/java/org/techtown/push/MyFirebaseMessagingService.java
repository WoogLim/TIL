package org.techtown.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

// FirebaseMessagingService : 구글 클라우드에서 보내오는 메시지를 이 클래스에서 받는다.
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FMS";

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onNewToken(String token){
        // 새로운 토큰을 확인했을 때 호출되는 메서드
        // 앱이 Firebase 서버에 등록되었을때 호출되는 메서드 앱의 ID를 등록
        super.onNewToken(token);
        Log.e(TAG, "onNewToken 호출됨: " + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        // 메시지 도착시 호출되는 메서드
        // RemoteMessage 객체의 정보를 확인하면 상대방이 클라우드 서버를 통해 보낸 푸시 메시지의 데이터를 확인할 수 있다.
        // content를 key로 하여 보내므로 수신할때도 content를 키로 확인할 수 있다.
        Log.d(TAG, "onMessageReceived 호출됨.");

        String from = remoteMessage.getFrom(); // 어디에서 보낸것인지 발신 정보 확인
        Map<String, String> data = remoteMessage.getData(); // 서버에서 전송한 데이터 확인
        String contents = data.get("contents"); // 키 값 contents로 발신 데이터 확인

        Log.d(TAG, "from : " + from + " contents : " + contents);
        sendToActivity(getApplicationContext(), from, contents);
    }

    private void sendToActivity(Context context, String from, String contents){
        // 액티비티 쪽으로 데이터를 보내기 위해 인텐트 객체를 만들고 startActivity 이용해 호출

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("contents", contents);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                        Intent.FLAG_ACTIVITY_SINGLE_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // 서비스에서 인텐트를 띄울땐 플래그를 설정해주어야한다.
        // 이미 메모리에 만들어진 경우 MainActivity의 onNewIntent 메서드로 데이터가 전달된다.

        context.startActivity(intent);
    }
}