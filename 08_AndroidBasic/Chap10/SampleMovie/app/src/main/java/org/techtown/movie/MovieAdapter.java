package org.techtown.movie;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    ArrayList<Movie> items = new ArrayList<Movie>();
    
    // 어뎁터 바인딩시 초기에 사용됨
    // 메모리에 적재한다음 쭉 사용
    // 리사이클러뷰는 100개의 리스트가 있다면 100개 전체를 생성하지 않는다. 화면에 보이는 리스트 갯수 + 위아래 버퍼를 위한 3개의 객체를 포함해 일부분만 생성하고
    // 나머지 뷰는 화면이 보여지는 시점에 스크롤 위로 넘어간 객체를 아래로 불러와 데이터를 변경시켜주는 원리이다.
    // onCreateViewHolder는 이 화면에 보일 리스트 갯수 + 버퍼를 위한 추가 객체 만을 생성해 몇번만 호출되고 더이상 호출되지 않음.
    // 리사이클러뷰에 반영하기 이전에 어뎁터 역할로 사전작업만 해주는 단계이다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext()); // 부모 뷰 그룹의 컨텍스트를 가져와 위치를 지정
        View itemView = inflater.inflate(R.layout.movie_item, viewGroup, false); // 부모 뷰 그룹에 레이아웃 형식을 인플레이트해 메모리에 적재하고 이를 사용하도록 해준다.

        return new ViewHolder(itemView); // 뷰홀더에 만들어진 리스트 레이아웃을 반영해준다.
    }

    // 뷰홀더에 데이터를 바인딩해주는 함수이다.
    // 리사이클러뷰가 스크롤되어 객체를 재사용할때 그 객체 레이아웃은 재사용하되 데이터는 새로 바뀌게 된다.
    // 아래에 새롭게 보여질 이 객체레이아웃의 인덱스 값이 position이라는 이름으로 넘겨진다.
    // onCreateViewHolder는 처음 화면에 적용하기 위한 객체 레이아웃을 만들기 때문에 화면에 보일 리스트 개수 + 위아래 버터 크기만큼 기본적으로 +3 번 호출되지만
    // onBindViewHolder는 스크롤해서 데이터가 반영될때마다 호출되므로 무한정 호출된다. 데이터가 100개 라면 100 호출됨. 리사이클러뷰는 100개 레이아웃을 만들지 않고
    // 화면에 보일 리스트 + 위아래 여유 버퍼 크기만큼해서 13번 정도 호출해 객체가 만들어지며 onBindViewHolder로 이를 재활용하는 셈이다.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Movie item = items.get(position); // 아래에 보여질 데이터 리스트가 10번째 데이터라면 10번째 데이터를 가져와서
        viewHolder.setItem(item); // 뷰홀더에 반영해준다.
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie item){
        items.add(item);
    }

    public void setItems(ArrayList<Movie> items){
        this.items = items;
    }

    public Movie getItem(int postion){
        return items.get(postion);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;

        // ViewHolder 는 데이터상 변경점이 일어나면 매번 호출됨 onCreateViewHolder 에 의해
        // 파라미터는 UI 리사이클러 뷰
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        textView1 = itemView.findViewById(R.id.textView);
        textView2 = itemView.findViewById(R.id.textView2);
    }

        // 리스트 형태로 보일 때 각각의 아이템은 뷰로 만들어지며 이 각각의 아이템을 위한 뷰는 뷰홀더에 담아두게 된다.
        public void setItem(Movie item){
            textView1.setText(item.movieNm);
            textView2.setText(item.audiCnt + "명");
        }
    }
}
