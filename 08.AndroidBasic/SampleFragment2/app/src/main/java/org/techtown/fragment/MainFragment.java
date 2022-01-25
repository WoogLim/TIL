package org.techtown.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
//  onCreateView는 뷰가 그려지는 시점에 호출된다. onCreate와 같다고 보면됨.
    // Fragment의 수명주기 (화면에 보일때)
    /* onAttach > onCreate > onCreateView > onActivityCreated > onStart > onResume 순으로 실행되므로 액티비티 설정관련부분은 onAttach에서 적용해야한다.
    * 액티비티가 메모리에 처음 만들어지면 onCreate 메서드가 호출되지만 프래그먼트는 onActivityCreated가 호출된다.
    * onAttach(Activity) : 프래그먼트가 액티비와 연결될 때 호출됨
    * onCreate(Bundle) : 프래그먼트가 초기활 될때 호출됨(new 로 객체를 만드는 시점이 아님.)
    * onCreateView(LayoutInflater, ViewGroup, Bundle) : 프래그먼트와 관련된 뷰 계층을 만들어 리턴. 뷰가 그려지는 시점에 호출되며 뷰의 계층구조를 구성하는 시점에 호출된다.
    * onActivityCreated(Bundle) : 프래그먼트와 연결된 엑티비티가 onCreate 메서드의 작업을 완료했을 때 호출
    * onStart() : 프래그먼트와 연결된 엑티비티가 onStart되어 사용자에게 프래그먼트가 보여질 때 호출
    * onResume() : 프래그먼트와 연결된 엑티비티가 onResume되어 사용자와 상호작용할 수 있을때 호출
    * */

    // Fragment의 수명주기 (화면에 보이지 않을때)
    /* onPause > onStop > onDestroyView(Baskstack에서 복구되는 경우 onCreateView 호출) > onDestroy > onDetach
    *  onPause() : 프래그먼트와 연결된 액티비티가 onPause되어 사용자와 상호작용을 중지할 때 호출
    *  onStop() : 프래그먼트와 연결된 액티비티가 onStop되어 화면에서 더 이상 보이지 않을 때나 프래그먼트의 기능이 중지되었을때 호출
    *  onDestroyView() : 프래그먼트와 관련된 뷰 리소스를 해제할 수 있도록 호출
    *  onDestroy() : 프래그먼트의 상태를 마지막으로 정리할 수 있도록 호출
    *  onDetach() : 프래그먼트가 액티비티와 연결을 끊기 직전 호출됨
    * */
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