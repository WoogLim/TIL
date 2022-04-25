package cs7_abstractClass;

//하위에서 상위의 추상 메서드를 정의하지 않으려면 하위 클래스도 abstract로 지정
public abstract class NoteBook extends Computer{

	@Override
	public void display() {
		System.out.println("NoteBook display");
	}

}
