package com.bignerdranch.android.geoguiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button

    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener{
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
    }

    // setResult로 결과 코드가 등록되면 안드로이드 OS에서 부모 액티비티인 MainActivity에서 다음 함수를 호출한다.
    // onActivityResult(requestCode: Int, resultCode: Int, data: Intent) 0, true, true
    // 첫 인자는 MainActivity에서 던진 요청 코드, 나머지 인자는 setResult(Int, Intent)
    private fun setAnswerShownResult(isAnswerShown : Boolean){
        val data = Intent().apply{
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        } // 객체 생성 data{ }
        setResult(Activity.RESULT_OK, data)
    }

    // 자바의 static과 유사 동반 객체 인스턴스화가 필요없음.
    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
