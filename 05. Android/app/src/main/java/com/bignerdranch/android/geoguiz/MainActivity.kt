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

// 자식 액티비티로 돌려받을 데이터
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var previousButton : ImageButton
    private lateinit var nextButton : ImageButton
    private lateinit var questionTextView : TextView
    private lateinit var cheatButton : Button

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
        questionTextView = findViewById(R.id.question_text_view);
        cheatButton = findViewById(R.id.cheat_button);

        // True 버튼
        trueButton.setOnClickListener{ view : View ->
            checkAnswer(true);
        }
        
        // False 버튼
        falseButton.setOnClickListener{ view : View ->
            checkAnswer(false);
        }

        // Previous 버튼
        previousButton.setOnClickListener{
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        // Next 버튼
        nextButton.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }

        cheatButton.setOnClickListener{
            // CheatActivity를 시작시킨다.
//            var intent = Intent(this, CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            // 액티비티 전환을 위해 사용
//            startActivity(intent)
            // 자식 액티비티로부터 리턴값을 전달받기 위해 사용
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
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

    // ViewModel, SIS
    /** saveInstanceState
     *  프로세스 종료, 장치의 구성이 변경에도 Bundle 객체를 사용해 엑티비티 레코드를 저장한다.
     *  엑티비티 최초 실행시 Bundel 객체 참조는 null. 화면 전환 등 구성 변경시 onSaveInstanceState(Bundle)을 호출 보존 데이터를 활용한다.
     *  이후 생성된 엑티비티 인스턴스에도 안드로이드 OS가 저장된 Bundle 상태를 onCreate(Bundle?)의 인자로 전달한다.
     **/
    /** ViewModel
     *  ViewModel은 엑티비티의 동적 데이터를 처리할 때 편리하다.
     *  구성 변경이 있더라도 다운로드 작업을 계속하게 해준다. 사용자가 엑티비티 종료시 자동으로 클린업된다.
     *  프로세스 종료시에는 ViewModel이 클린업을 처리하지 못하며 자신이 가진 프로세스와 함께 메모리에서 제거된다.
     *  SIS가 주목받는 이유가 이때문이다. 허나 제약이 존재한다. SIS는 직렬화되어 디스크에 저장되므로 크거나 복잡한 객체는 피해야한다.
     *  !) lifecycle-viewmodel-savedstate 라이브러리 배포로 인해 해당 단점 또한 완화 가능. 두 가지를 적절히 사용하면 좋다.
     *  소량의 정보는 SIS, 많은 데이터 저장 및 캐시 저장을 위해서는 ViewModel을 사용한다.
     **/
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
//        Log.d(TAG, "Updating question text", Exception())
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