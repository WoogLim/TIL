package cs1_Object;

class Book {
	private String title;
	private String author;
	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	@Override
	public String toString() {
		return title + ", " + author;
	}
	
}

public class BookTest {
	public static void main(String[] args) {
		
		Book book = new Book("데미안", "헤르만 헤세");
		
		System.out.println(book);
//		cs1_Object.Book@7c30a502
//		Book@7c30a502 > 메모리 주소
		
		String str = new String("test");
		System.out.println(str); // 메모리 주소가 나오지 않음.
								 // toString이 재정의되어있으므로
		
	}
}
