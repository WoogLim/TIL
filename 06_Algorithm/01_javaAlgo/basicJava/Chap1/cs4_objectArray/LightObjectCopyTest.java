package cs4_objectArray;

public class LightObjectCopyTest {

	public static void main(String[] args) {

		Book[] library = new Book[5];
		Book[] copyLibrary = new Book[5];
		
		library[0] = new Book("�¹���1", "������");
		library[1] = new Book("�¹���2", "������");
		library[2] = new Book("�¹���3", "������");
		library[3] = new Book("�¹���4", "������");
		library[4] = new Book("�¹���5", "������");
		
		// library�� 0��° �ε��� copyLibrary�� 0��° �ε��� �� 5�� ����(�ּҸ� ����) �ּҵ� �Ȱ��� ���� �����Ѵ�.
		System.arraycopy(library, 0, copyLibrary, 0, 5);
		
		// ���� ������ ��. �ΰ��� �迭�� ������ ��ħ. �Ѵ� ���� �ε����� �����ϹǷ�.
		library[0].setAuthor("�ڿϼ�");
		library[0].setTitle("����");
		
		for(Book book : library) {
			System.out.println(book);
			book.showInfo();
		}
		
		for(Book book : copyLibrary) {
			System.out.println(book);
			book.showInfo();
		}
	}
}
