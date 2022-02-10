package org.techtown.recyclerview2;

import android.view.View;

public interface OnPersonItemClickListener {
    // 뷰홀더 내부에서 onClickListener 선언하는 경우 해당 이벤트로 값이 변경될 때마다 뷰홀더가 재구성되어야된다는 문제점이 있다.
    // 따라서 외부에서 이를 적용하도록 해야한다.

    // onItemClick 메서드가 호출되는 경우 파라미터로 뷰홀더와 뷰객체 그리고 뷰의 position 정보가 전달되도록 한다.
    public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position);
}
