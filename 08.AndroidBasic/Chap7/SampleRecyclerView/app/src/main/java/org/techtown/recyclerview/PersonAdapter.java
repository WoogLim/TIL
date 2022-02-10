package org.techtown.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{
    // Person 객체를 담는 ArrayList를 생성한다. 여러 아이템을 이 어뎁터에서 관리하기 위해 ArrayList에 담는다.
    ArrayList<Person> items = new ArrayList<Person>();

    // RecyclerView.Adapter 추상클래스의 의 구현 메서드
    // 뷰홀더내 아이템은 객체에 모두 저장하지 않고 내부 캐시에 저장하며 보여야하는 순간에 뷰홀더에 뷰 객체를 넣고 스크롤하여 보이지 않게 된 부분은
    // 새로 보이는 순간에 재사용한다. 이 과정에서 뷰 홀더가 재사용된다.

    // 뷰홀더가 만들어질때 호출되는 메서드
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // 뷰 홀더가 만들어지는 순간에 아이템을 위해 정의한 XML 레이아웃을 이용해 뷰 객체를 만들어준다. 뷰 객체를 새로만든 뷰 홀더에 담아 반환해준다.
        // viewType : 뷰 타입을 위한 정수값 각각의 뷰 타입에 따라 다른 XML 레이아웃을 인플레이션하여 보여줄 수 있다.
        // onCreateViewHolder 메서드 안에서 인플레이션을 진행하려면 Context 객체가 필요한데 파라미터로 전달되는 뷰그룹 객체의 getContext 메서드를 이용하면 Context객체를 참조할 수 있다.
        // 뷰그룹 객체는 각 아이템을 위한 뷰그룹 객체이므로 XML레이아웃을 인플레이션해 이 뷰그룹 객체에 설정한다.
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);

        return new ViewHolder(itemView);
    }

    // 뷰홀더가 재사용될때 호출되는 메서드
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // 뷰 객체를 기존의 뷰 홀더에 담고 데이터만 바꿔준다.
        Person item = items.get(position);
        viewHolder.setItem(item);
    }

    // 어뎁터에서 관리하는 아이템의 갯수 반환
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 외부 클래스에서 이 어뎁터의 아이템을 변경할 수 있도록 addItem, setItems, getItem, setItem 을 추가한다. 이후 onBindViewHolder 수행됨.
    public void addItem(Person item){
        items.add(item);
    }

    public void setItems(ArrayList<Person> items){
        this.items = items;
    }

    public Person getItems(int position){
        return items.get(position);
    }

    public void setItem(int position, Person item){
        items.set(position, item);
    }
    // 이 리싸이클러뷰의 어뎁터 코드가 여기까지임 이 어뎁터는 리싸이클러뷰에 설정되어 어뎁터 안에 Person 객체를 넣고
    // MainActivity.java 파일을 열어 onCreate 메서드에 추가해준다.


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        // 뷰 홀더 생성자로 전달되는 뷰 객체 참조
        // 뷰 객체는 super를 통해 부모로 전달된다. 전달받은 뷰 객체의 이미지 텍스트뷰등을 findViewById메서드로 찾아
        // 할당하면 setItem 을 참조할 수 있으며 이 메서드는 뷰홀더에 들어있는 뷰 객체를 다르게 보이게 만든다.
        public ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 들어 있는 텍스트뷰 참조
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void setItem(Person item){
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }
}
