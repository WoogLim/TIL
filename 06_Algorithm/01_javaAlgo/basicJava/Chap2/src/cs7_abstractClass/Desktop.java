package cs7_abstractClass;

// 하위에서 상위의 추상 메서드를 정의하지 않으려면 하위 클래스도 abstract로 지정
// 만일 메서드가 모두 구현이 되어있어도 abstract로 정의할 수 있다. -> 이 경우 해당 클래스는 인스턴스화 불가능 상속만을 위한 클래스로 주로 이용
// public abstract class Desktop extends Computer

public class Desktop extends Computer{

	@Override
	public void display() {
		System.out.println("Desktop display");
	}

	@Override
	public void typing() {
		System.out.println("Desktop Typing");
	}

	// 재정의도 가능
	@Override
	void turnOff() {
		System.out.println("Desktip TurnOff");
	}
	
}
