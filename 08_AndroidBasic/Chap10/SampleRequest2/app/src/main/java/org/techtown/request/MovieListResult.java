package org.techtown.request;

import java.util.ArrayList;

public class MovieListResult {

    String boxofficeType;
    String showRange;

    // JSON 응답 문자열이 배열인 경우 즉 대괄호료 표시된 경우 클래스를 정의할 때 ArrayList 자료형을 사용할 수 있다.
    ArrayList<Movie> dailyBoxOfficeList = new ArrayList<Movie>();
}
