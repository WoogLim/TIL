package org.techtown.push.send;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    // RequestQueue 객체에 요청 객체(RequestObject)를 만들어 추가시 자동으로 메시지 전송
    static RequestQueue requestQueue;
    static String regId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                send(input);
            }
        });

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void send(String input){
        JSONObject requestData = new JSONObject(); // 전송 정보를 담아둘 JsonObject 생성 다른 메시지 전송 시 다른 JsonObject를 추가로 담아주면 된다.

        try{
            requestData.put("priority", "high"); // 옵션 추가

            // 전송할 데이터 추가
            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input); // 메시지 내용
            requestData.put("data", dataObj);

            // 푸시 메시지를 수신할 단말의 등록 ID를 JSONArray에 추가 후 requestData 객체에 추가
            JSONArray idArray = new JSONArray();
            // regId는 SamplePush에서 확인된 등록 id를 복사하여 넣어주어야한다.
            // 지금은 push에서 등록한 id를 여기에 전달할 서버를 만들지 않았으므로 직접 복사해준다.
            regId = "등록 Id";
            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);
        }catch(Exception e){
            e.printStackTrace();
        }

        // 메시지를 전송하면서 상태정보 를 리스너로 출력
        sendData(requestData, new SendResponseListener(){
            // 푸시 전송을 위해 정의한 메서드 호출
            @Override
            public void onRequestCompleted(){
                println("onRequestCompleted 호출됨");
            }

            @Override
            public void onRequestStarted(){
                println("onRequestStarted 호출됨");
            }

            @Override
            public void onRequestWithError(VolleyError error){
                println("onRequestWithError 호출됨");
            }
        });
    }

    public void println(String data){
        textView.setText(data + "\n");
    }

    public interface SendResponseListener {
        public void onRequestCompleted();
        public void onRequestStarted();
        public void onRequestWithError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener){
        // Volley 요청 객체를 만들고 요청을 위한 데이터 설정
        // 클라우드로 메시지 전송시 Volley, OkHttps, retrofit 라이브러리 등을 사용할 수 있다. Volley 라이브러리를 사용하므로 JsonObjectRequest객체를 사용
        // Request.Method.POST : POST 방식으로 요청
        // https://fcm.googleapis.com/fcm/send : url (클라우드 서버)
        // requestData : 요청 데이터
        // Response.Listener : 성공
        // Response.ErrorListener : 실패
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onRequestWithError(error);
                    }
                }
        ){
            // 요청을 위한 파라미터 설정
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            // 요청을 위한 파라미터 설정
            @Override
            public Map<String, String> getHeaders() throws  AuthFailureError{
                Map<String, String> headers = new HashMap<String, String>();

                // 서버 토큰 키
                headers.put("Authorization",
                            "서버 토큰 키");
                return headers;
            }
            @Override
            public String getBodyContentType(){
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        // Volley Quere 에 요청 객체 추가
        // Http 통신을 하므로 Internet 권한을 매니페이스트에 추가해야한다.
        requestQueue.add(request);
    }
}