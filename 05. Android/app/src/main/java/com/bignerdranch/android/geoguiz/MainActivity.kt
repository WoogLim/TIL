package com.bignerdranch.android.geoguiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// 최상위 수준 속성
// 특정 클래스의 인스턴스를 생성하지 않고 앱이 실행되는 동안 계속 보존
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var previousButton : ImageButton
    private lateinit var nextButton : ImageButton
    private lateinit var questionTextView : TextView
    
    // listof 는 List를 생성하는 코틀린의 컬렉션 함수
    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false))
    
    private var currentIndex = 0;

    private var score = 0;

    private var total = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // public static Int d(String tag, String msg) 이용
        // ERROR Log.e() 에러
        // WARNING Log.w() 경고
        // INFO Log.i() 정보성 메시지
        // DEBUG Log.d() 디버깅 출력
        // VERBOSE Log.v() 개발 전용
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view)

        // True 버튼
        trueButton.setOnClickListener{ view : View ->
//            var toast = Toast.makeText(
//                this,
//                R.string.correct_toast,
//                Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, Gravity.CENTER, 550);
//            toast.show();
            checkAnswer(true);
        }
        
        // False 버튼
        falseButton.setOnClickListener{ view : View ->
//            var toast = Toast.makeText(
//                this,
//                R.string.incorrect_toast,
//                Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, Gravity.CENTER, 550);
//             JAVA 11, API 30 은 setGravity 옵션이 없어 미적용 API 28 이하로 낮추거나 Snakbar 사용 권장
//            toast.show();

            // Snackbar
//            var snackbar = Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT);
//            snackbar.setAction("show Toast"){
//                val toastFalse = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT);
//                toastFalse.setGravity(Gravity.TOP, Gravity.CENTER, 550);
//                toastFalse.show();
//            }.show()
            checkAnswer(false);
        }

        // Previous 버튼
        previousButton.setOnClickListener{
            currentIndex = if(currentIndex < 1){
                (questionBank.size - 1);
            }else{
                currentIndex - 1
            }
            updateQuestion()
        }

        // Next 버튼
        nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
//            val questionTextResId = questionBank[currentIndex].textResId
//            questionTextView.setText(questionTextResId)
            updateQuestion()
        }

        questionTextView.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
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

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    // 뷰로 부터 받은 값 확인
    private fun checkAnswer(userAnswer : Boolean){
        if(questionBank[currentIndex].done == true){
            Toast.makeText(this, R.string.done_toast, Toast.LENGTH_SHORT).show()
            return;
        }

        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
            score + 1;

        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun totalScore(){
        val score : Int = ((this.score/questionBank.size)*100)
        var scoreMsg : String = score.toString()+"점 입니다."
        Toast.makeText(this, scoreMsg, Toast.LENGTH_SHORT).show()
    }
}