package org.techtown.recyclerview2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// 어뎁터 클래스가 새로 정의한 리스너 인터페이스 구현하도록 하기
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> implements OnPersonItemClickListener{
    ArrayList<Person> items = new ArrayList<Person>();
    OnPersonItemClickListener listener;

    // 뷰홀더가 만들어질때 호출되는 메서드
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);

        // 리스너인 this(onCreateViewHolder) 를 전송한다.
        return new ViewHolder(itemView, this);
    }

    // 외부에서 리스너를 추가하도록 설정 -> 설정이 되면 다음 onItemClick 메서드가 호출될때 이 리스너가 실행된다.
    public void setOnClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    // OnPersonItemClickListener 의 구현 메서드 뷰홀더 클래스 안에서 뷰가 클릭되었을때 호출되는 메서드
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
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


    // 뷰홀더 객체의 생성자가 호출될 때 리스너 객체가 파라미터로 전달된다. 이 리스너 객체는 어댑터 밖에서 설정할 것이며 뷰홀더까지 전달된다.
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView, final OnPersonItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            // 아이템 뷰에 OnclickListener 설정하기
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    // getAdapterPosition 이 뷰홀더에 표시할 아이템이 어뎁터에서 몇 번째 인지 반환 즉 아이템의 인덱스 정보를 반환한다.
                    int position = getAdapterPosition();
                    // 아이템 뷰 클릭시 미리 정의한 다른 리스너의 메서드 호출
                    if (listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Person item){
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }
}
