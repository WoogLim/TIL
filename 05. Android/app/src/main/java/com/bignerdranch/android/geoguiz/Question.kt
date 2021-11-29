package com.bignerdranch.android.geoguiz

import androidx.annotation.StringRes

//class Question {
//}

// @StringRes Annotation : 생성자에서 유효 문자열 리소스 Id 제공하는지 컴파일 시점에서 Lint가 검사
// data 클래스는 비즈니스 로직을 처리하는 함수보다 데이터를 저장하는 속성을 갖는 용도로 많이 사용한다. VO(모델 class)와 유사
// class 인스턴스간 속성 값을 비교, 인스턴스 컬렉션 저장 시 키값을 생성하는 기능, 속성값을 문자열로 출력하는(toString()) 기능이 지원됨.
data class Question(@StringRes val textResId : Int, val answer : Boolean, val done : Boolean)

// 안드로이드는 MVC패턴을 채용해 설계됨.

// 모델 객체 : 데이터, 비즈니스 로직을 가짐
// 상품, 서버에 저장된 사진 등 데이터를 저장하기 위함 혹은, true, false 여부를 확인할 로직을 가진다.
// 커스텀 클래스다. app의 모든 객체들은 모델 계층으로 구성됨

// 뷰 객체 : 눈에 보이는 화면

// 컨트롤러 객체 : 뷰와 모델을 결속해 애플리케이션 로직을 포함 이벤트에 응답하며 모델 객체, 뷰 계층과 주고받은 데이터 흐름을 관리
// 주로 Activity, Fragment 서브클래스