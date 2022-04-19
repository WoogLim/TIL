package cs6_ArrayList;

import java.util.ArrayList;
import cs4_objectArray.Book;

public class ArrayListTest {
	
	public static void main(String[] args) {
		
//		<> 제너릭 : 어떤 객체를 넣을건지 지정 인스턴스화시 <>내에 Book은 생략이 됨.
		ArrayList<Book> library = new ArrayList<>();
		
		library.add(new Book("태백산맥1", "조정래"));
		library.add(new Book("태백산맥2", "조정래"));
		library.add(new Book("태백산맥3", "조정래"));
		library.add(new Book("태백산맥4", "조정래"));
		library.add(new Book("태백산맥5", "조정래"));
	
		for(int i = 0; i < library.size(); i++) {
			library.get(i).showInfo();
		}
	}
}
