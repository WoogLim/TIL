package com.bignerdranch.android.geoguiz

import androidx.annotation.StringRes

//class Question {
//}

// @StringRes Annotation : 생성자에서 유효 문자열 리소스 Id 제공하는지 컴파일 시점에서 Lint가 검사
// data 클래스는 비즈니스 로직을 처리하는 함수보다 데이터를 저장하는 속성을 갖는 용도로 많이 사용한다. VO(모델 class)와 유사
// class 인스턴스간 속성 값을 비교, 인스턴스 컬렉션 저장 시 키값을 생성하는 기능, 속성값을 문자열로 출력하는(toString()) 기능이 지원됨.
data class Question(@StringRes val textResId : Int, val answer : Boolean)