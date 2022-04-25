package cs7_abstractClass;

// abstract 메서드가 포함되면 클래스를 abstract로 정의할것
public abstract class Computer {
	
	// 부모가 정의하지 못하는 부분은 하위클래스들이 정의하도록 지정
	// 하위에게 책임을 전가
	public abstract void display();
	public abstract void typing();
	
	// 공통으로 하위에서 사용할 메서드 
	void turnOn() {
		
		System.out.println("전원을 켭니다.");
		
	}
	
	void turnOff() {
		
		System.out.println("전원을 끕니다.");
	
	}
}
