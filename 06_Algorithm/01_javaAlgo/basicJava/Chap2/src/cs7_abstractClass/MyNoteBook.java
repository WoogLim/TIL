package cs7_abstractClass;

public class MyNoteBook extends NoteBook{

	// NoteBook 에서 구현하지 않은 메서드 구현
	@Override
	public void typing() {
		System.out.println("MyNoteBook typing");
	}
	

}
