package org.techtown.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";

    // action-filter 를 SMS 메시지 액션으로 설정했으므로 SMS를 받으면 onReceive 가 호출된다.
    // 파라미터로 전달되는 intent 객체 안에 SMS 데이터가 들어 있다. SMS 수신시 매니페스트에 권한을 추가한다.
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive() 메서드 호출됨.");

        Bundle bundle = intent.getExtras(); // 인텐트에서 Bundle 객체 가져오기
        SmsMessage[] messages = parseSmsMessage(bundle); // SmsMessage타입의 배열 객체를 반환하는 parseSmsMessage 메서드 호출

        if(messages != null && messages.length > 0){
            String sender = messages[0].getOriginatingAddress(); // 발신자 정보
            Log.i(TAG, "SMS sender : " + sender);

            String contents = messages[0].getMessageBody(); // 문자 내용 -> 인증번호 자동으로 입력해줄때 사용하는듯?
            Log.i(TAG, "SMS contents : " + contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis()); // 문자를 받은 시각
            Log.i(TAG, "SMS received date : " + receivedDate.toString());

        }
    }

    // SMS 데이터를 확인할 수 있도록 안드로이드 API에 정해둔 코드 사용
    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus"); // Bundle 객체에 들어가 있는 부가 데이터 중 pdus 가져오기
        SmsMessage[] messages = new SmsMessage[objs.length]; // 메시지 갯수 만큼 SmsMessage타입 배열

        int smsCount = objs.length;
        for(int i = 0; i < smsCount ; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ // API 23 버전보다 높은 경우
                // 단말 OS 버전에 따라 다른 방식으로 메서드 호출하기
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else{
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }

        return messages;
    }
}
