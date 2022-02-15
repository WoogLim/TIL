package org.techtown.request;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    // 큐는 한 번만 만들면 계속 사용이 가능하므로 static으로 생성
    // 요청 큐는 하나의 액티비티가 아닌 전체 액티비티에서 사용하는 경우가 일반적임. 따라서 실제 앱을 만드는 경우 Application 클래스 안에 넣어두거나
    // 별도의 클래스를 생성해 넣어둘 수 있다.
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        // RequestQueue 요청 큐 객체 생성
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void makeRequest(){
        String url = editText.getText().toString();

        // 요청을 보내기 위한 StringRequest 객체 생성 - 문자열을 주고 받기 위한 요청 객체
        // rest api 요청 방식, url, 성공, 에러 리스너 등록
        // 스레드와 핸들러 없이 값을 요청해 UI에 반영할 수 있다.
        StringRequest request = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    println("응답 ->" + response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    println("에러 ->" + error.getMessage());
                }
            }
        )
        // POST 의 경우 getParams에 파라미터 값을 넣어 요청 파라미터를 전달할 수 있다. 현재는 GET이므로 사용하지않음.
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        // 요청큐는 캐쉬 메커니즘을 지원한다. 이전 응답 결과를 사용하지 않을 경우 false
        request.setShouldCache(false);
        // 요청 큐에 넣어 요청 보내기
        requestQueue.add(request);
        println("요청 보냄");
    }

    public void println(String data){
        textView.setText(data + "\n");
    }
}