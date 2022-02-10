package org.techtown.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        webView = findViewById(R.id.webView);

        // 웹뷰의 설정 수정 자바스크립트 사용 설정 및 확대/축소 기능 설정 가능
        WebSettings webSettings = webView.getSettings();
        // 자바스크립트가 동작할 수 있는 환경이 됨.
        webSettings.setJavaScriptEnabled(true);
        // 웹뷰 객체에 웹사이트를 보여주기 위해 WebViewClient를 상속한 객체를 만들어 webView에 설정한다.
        webView.setWebViewClient(new ViewClient());

        // 버튼 클릭시 사이트 로딩
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // loadUrl 원격지의 웹페이지 혹은 로컬의 페이지로 이동할 수 있다.
                webView.loadUrl(editText.getText().toString());
            }
        });

    }

    private class ViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            view.loadUrl(url);
            return true;
        }
    }
}