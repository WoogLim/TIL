package cs6_ArrayList;

import java.util.ArrayList;
import cs4_objectArray.Book;

public class ArrayListTest {
	
	public static void main(String[] args) {
		
//		<> ���ʸ� : � ��ü�� �������� ���� �ν��Ͻ�ȭ�� <>���� Book�� ������ ��.
		ArrayList<Book> library = new ArrayList<>();
		
		library.add(new Book("�¹���1", "������"));
		library.add(new Book("�¹���2", "������"));
		library.add(new Book("�¹���3", "������"));
		library.add(new Book("�¹���4", "������"));
		library.add(new Book("�¹���5", "������"));
	
		for(int i = 0; i < library.size(); i++) {
			library.get(i).showInfo();
		}
	}
}
