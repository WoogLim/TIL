package org.techtown.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    TextView textView1;
    TextView textView2;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String data = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
    }

    // 핸들러를 통해 UI 조작
    public void printClientLog(final String data){
        Log.d("MainActivity", data);

        handler.post(new Runnable() {
            @Override
            public void run() {
                textView1.append(data + "\n");
            }
        });
    }
    public void printServerLog(final String data){
        Log.d("MainActivity", data);

        handler.post(new Runnable() {
            @Override
            public void run() {
                textView2.append(data + "\n");
            }
        });
    }

    public void send(String data){
        try{
            int portNumber = 5001;
            // 소켓 객체 생성
            Socket sock = new Socket("localhost", portNumber);
            printClientLog("소켓 연결함.");
            
            // 소켓 객체로 데이터 보내기
            ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
            outstream.writeObject(data);
            outstream.flush();
            printClientLog("데이터 전송함.");

            ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
            printClientLog("서버로부터 받음: " + instream.readObject());
            sock.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void startServer(){
        try{
            int portNumber = 5001;

            // 소켓 서버 객체 만들기
            ServerSocket server = new ServerSocket(portNumber);
            printServerLog("서버 시작함" + portNumber);

            // 클라이언트 접속을 기다리다가 접속했을 때 만들어지는 소켓 객체 참조하기
            while(true){
                // 기다리다가 접속을 하는경우 accept 메서드로 소켓 객체 반환
                Socket sock = server.accept();
                InetAddress clientHost = sock.getLocalAddress();
                int clientPort = sock.getPort();
                printServerLog("클라이언트 연결됨: " + clientHost + " : " + clientPort);

                ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
                Object obj = instream.readObject();
                printServerLog("데이터 받음: " + obj);

                ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
                outstream.writeObject(obj + " from Server");
                outstream.flush();
                printServerLog("데이터 보냄.");

                sock.close();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}