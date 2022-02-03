package org.techtown.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 위험 권한을 부여할 권한 지정 SD 카드 접근을 위해 2개의 권한 필요
        // 이 권한은 위험 권한이므로 앱이 실행될 때 사용자에게 권한을 부여해 달라는 대화상자를 띄어야 한다.
        // 현재는 앱이 실행되는 시점에 요구 했으나 실행중 접근이 필요할때 요구할 수 있다.
        String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        checkPermissions(permissions);
    }

    public void checkPermissions(String[] permissions){
        // 권한이 부여되어 있지 않은 경우 ArrayList에 담는다.
        ArrayList<String> targetList = new ArrayList<String>();

        for(int i = 0 ; i < permissions.length; i++){
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission); // 해당 권한이 부여되어 있는지 확인
            if(permissionCheck == PackageManager.PERMISSION_GRANTED){
                // 권한이 부여되어있는 경우
                Toast.makeText(this, curPermission + " 권한 있음.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, curPermission + " 권한 없음.", Toast.LENGTH_SHORT).show();
                // 권한 설명을 위해 ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission) 사용
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)){
                    // 권한 요청을 거부한 경우
                    Toast.makeText(this, curPermission + " 권한 설명 필요함.", Toast.LENGTH_SHORT).show();
                } else {
                    // 권한 요청을 처음 보거나 다시 묻지 않음 선택한 경우. 권한을 허용한 경우.
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        // 권한부여가 안된 경우 권한 부여 요청 대화상자를 띄운다.
        ActivityCompat.requestPermissions(this, targets, 101); // 위험 권한 부여 요청하기
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}