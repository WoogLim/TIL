package cs7_abstractClass;

public class ComputerTest {
	
	public static void main(String[] args) {
		
		// 추상클래스는 인스턴스화가 불가능
		// 구현된 하위 클래스를 인스턴스화한다.
//		Computer computer = new Computer(); X
		
		Computer desktop = new Desktop();
		
		desktop.display();
		
	}

}
