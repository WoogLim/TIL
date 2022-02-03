package org.techtown.receiver;

import androidx.appcompat.app.AppCompatActivity;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import android.os.Bundle;

// MainActivity가 인터페이스 구현하도록 하기
public class MainActivity extends AppCompatActivity implements AutoPermissionsListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}