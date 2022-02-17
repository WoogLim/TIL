package org.techtown.movie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    RecyclerView recyclerView;
    MovieAdapter adapter;

    // 리퀘스트 큐 생성 Volley 라이브러리에서 지원 한번 초기화 후 계속 사용할것이므로 메모리에 한번 적재하는 static으로 초기화
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

        // 큐 초기화
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        // XML 레이아웃에 정의한 리사이클러뷰 객체 참조
        recyclerView = findViewById(R.id.recyclerView);

        // 리사이클러뷰 내부 모양 지정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // 리사이클러뷰에 어댑터 설정하기 현재는 데이터를 받아오는 응답 시점에 데이터를 반영할 것이므로 어뎁터만 생성해준다.
        adapter = new MovieAdapter();
        // 만들어진 리사이클러뷰에 어뎁터를 설정해준다. 어뎁터와 상호작용하면서 리스트 모양으로 보여준다.
        recyclerView.setAdapter(adapter);
    }

    public void makeRequest(){
        String url = editText.getText().toString();

        // 요청 객체. 문자열 형식으로 데이터 요청 후 응답/에러 리스너 처리
        StringRequest request = new StringRequest(
            Request.Method.GET,
            url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    println("응답 ->" + response);

                    processResponse(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    println("에러 ->" + error.getMessage());
                }
            }
        ){
            // 코드블럭 초기화 메소드 호출시 바로 호출
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄");
    }

    public void println(String data){
        Log.d("MainActivity", data);
    }

    // Jackson 프레임워크와 유사한 방식으로 생각하면됨.
    public void processResponse(String response){
        // 응답 문자열 java 객체로 만들기 Gson 라이브러리 사용
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response, MovieList.class); // 응답받은 json 문자열을 MovieList로 변환하기

        println("영화 정보 수: " + movieList.boxOfficeResult.dailyBoxOfficeList.size());

        for(int i = 0 ; i < movieList.boxOfficeResult.dailyBoxOfficeList.size() ; i++){
            Movie movie = movieList.boxOfficeResult.dailyBoxOfficeList.get(i);

            // Movie 객체들을 하나씩 꺼내 어뎁터에 추가해준다.
            adapter.addItem(movie);
        }

        // 어뎁터를 모두 추가한뒤 DataSetChanged 메서드를 호출해 반영해준다.
        // 만일 이미지에 각 영화 이미지를 넣고싶다면 이미지를 웹 서버에서 받아와야 한다. 자주 사용하는 라이브러리로 Glide나 Picasso 등이 있다.
        adapter.notifyDataSetChanged();
    }
}