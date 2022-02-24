package org.techtown.album;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    public void openGallery(){
        Intent intent = new Intent();
        // 타입 지정
        intent.setType("image/*");
        // 액션 지정 MIME 타입이 image로 시작하는 데이터를 가져오라는 의미
        intent.setAction(Intent.ACTION_GET_CONTENT);

        // 명시한 인텐트 실행.
        startActivityForResult(intent, 101); // 해당 인텐트가 종료되면 결과값 받아옴.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                // Uri의 자료형 값 반환됨. 이 값은 ContentResolver를 통해 참조할 수 있는 이미지를 가리킴.
                // 이미지는 보통 SD카드에 저장되므로 매니페스트에 권한 부여 필요
                Uri fileUri = data.getData();

                ContentResolver resolver = getContentResolver(); // 내용 제공자 참조

                try{
                    // openInputStream 메서드로 InputStream 객체가 반환됨.
                    InputStream instream = resolver.openInputStream(fileUri);
                    // BitmapFactory.decodeStream 메서드를 이용해 Bitmap 객체 반환
                    Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                    imageView.setImageBitmap(imgBitmap);

                    instream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}