package org.techtown.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
         * inflater.inflate()
         * onCreateView의 첫번째 객체로 inflate 메서드를 바로 호출하여 반환
         * - inflater의 첫번째 인자로는 XML 레이아웃 파일
         * - 두번 째 인자로 이 XML이 그려질 뷰 그룹 객체이며 onCreateView의 메서드로 전달되는
         * 두 번째 파라미터가 이 프래그먼트의 최상위 레이아웃이 된다.
         * inflate의 결과로 새로운 ViewGroup객체가 만들어진다.
         *
         * 이 객체를 메인엑티비티에 추가하는 방법은 두가지이다.
         * 1. 메인 엑티비티 XML에 추가하는 방식
         * 2. 엑티비티 소스 코드에서 추가하는 방식
         *
         * 이 예제에서는 1번 방법 이용.
         * */
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}