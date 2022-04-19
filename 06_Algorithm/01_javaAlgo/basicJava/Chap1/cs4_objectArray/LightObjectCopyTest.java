package cs4_objectArray;

public class LightObjectCopyTest {

	public static void main(String[] args) {

		Book[] library = new Book[5];
		Book[] copyLibrary = new Book[5];
		
		library[0] = new Book("태백산맥1", "조정래");
		library[1] = new Book("태백산맥2", "조정래");
		library[2] = new Book("태백산맥3", "조정래");
		library[3] = new Book("태백산맥4", "조정래");
		library[4] = new Book("태백산맥5", "조정래");
		
		// library의 0번째 인덱스 copyLibrary의 0번째 인덱스 총 5번 복사(주소를 복사) 주소도 똑같은 곳을 참조한다.
		System.arraycopy(library, 0, copyLibrary, 0, 5);
		
		// 얕은 복사라고 함. 두개의 배열에 영향을 미침. 둘다 같은 인덱스를 참조하므로.
		library[0].setAuthor("박완서");
		library[0].setTitle("나무");
		
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
