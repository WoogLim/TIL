package com.bignerdranch.android.geoguiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

// ViewModel 사용시 build.gradle 에 dependency에 추가해주어야한다. ViewModel 은 lifeCycle의 서브 클래스이다.
// ViewModel 의 생명주기는 장치의 구성 변경이 생겨도 계속 존재하고 엑티비티 종료시에만 소멸된다. 즉 액티비티 상태 변화와 무관하게 액티비티가 종료될 때까지 메모리에 남는다.
// Activity.isFinishing으로 구분이 가능하다 true인 경우 사용자가 종료한 경우(앱 완전 종료) false인 경우는 화면 전환으로 구분 가능하다.
class QuizViewModel : ViewModel() {
    //    init{
    //        Log.d(TAG, "ViewModel instance created")
    //    }
    //
    //    override fun onCleared() {
    //        super.onCleared()
    //        Log.d(TAG, "ViewModel instance about to be destroyed")
    //    }

    var currentIndex : Int = 0;

    var issueSeq = 0;

    // listof 는 List를 생성하는 코틀린의 컬렉션 함수
    private var questionBank = listOf(
        Question(R.string.question_australia, true, false, false),
        Question(R.string.question_oceans, true, false, false),
        Question(R.string.question_mideast, false, false, false),
        Question(R.string.question_africa, false, false, false),
        Question(R.string.question_americas, true, false, false),
        Question(R.string.question_asia, true, false, false))

    val currentQuestionAnswer : Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText : Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size;
    }

    fun moveToPrevious(){
        currentIndex = if(currentIndex < 1){
            (questionBank.size - 1);
        }else{
            currentIndex - 1
        }
    }
}