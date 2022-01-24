package org.techtown.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        // 최상위 레이아웃은 메인 프래그먼트 안에 들어 있다.
        // 메인 프래그먼트는 이 레이아웃을 화면에 보여주기 위한 틀이다.

        // 우선 메모리에 뷰그룹을 적재한뒤 객체를 참조한다.
        Button button = rootview.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 프래그먼트는 엑비티티를 종속하므로 메인엑티비티를 참조한다.
                MainActivity activity = (MainActivity) getActivity();
                // 프래그먼트는 액티비티를 본떠 만들었다. 액티비티 관리를 시스템에서 하는 것과 같이
                // 프래그먼트는 액티비티에서 관리된다.
                activity.onFragmentChanged(0);
            }
        });
        return rootview;
    }
}