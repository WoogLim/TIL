package com.bignerdranch.android.geoguiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

// 최상위 수준 속성
// 특정 클래스의 인스턴스를 생성하지 않고 앱이 실행되는 동안 계속 보존
private const val TAG = "MainActivity"

// Bundle 객체에 저장될 데이터의 키
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var previousButton : ImageButton
    private lateinit var nextButton : ImageButton
    private lateinit var questionTextView : TextView

    // ViewModelProvider(this).get(QuizViewModel::class.java)
    //        val provider : ViewModelProvider = ViewModelProvider(this)
    //        val quizViewModel = provider.get(QuizViewModel::class.java)
    //        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

    // quizViewModel을 var이 아닌 val 속성으로 선언이 가능하다. ViewModel은 인스턴스 생성시 한번만 저장하기 때문에 lazy를 사용한다.
    // 최초 사용시에만 해당 인스턴스를 생성한다. 초기화를 늦출수 있음.
    private val quizViewModel : QuizViewModel by lazy{
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // public static Int d(String tag, String msg) 이용
        // ERROR Log.e() 에러
        // WARNING Log.w() 경고
        // INFO Log.i() 정보성 메시지
        // DEBUG Log.d() 디버깅 출력
        // VERBOSE Log.v() 개발 전용
        Log.d(TAG, "onCreate(Bundle?) called");
        setContentView(R.layout.activity_main);

        // 값이 있다면 저장. 없거나 null 이면 0으로 저장
        // 인스턴스 최초 생성시 null
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX,0) ?: 0;
        quizViewModel.currentIndex = currentIndex;

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view)

        // True 버튼
        trueButton.setOnClickListener{ view : View ->
            checkAnswer(true);
        }
        
        // False 버튼
        falseButton.setOnClickListener{ view : View ->
            checkAnswer(false);
        }

        // Next 버튼
        nextButton.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.d(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex);
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    // 뷰로 부터 받은 값 확인
    private fun checkAnswer(userAnswer : Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (userAnswer == correctAnswer){
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}