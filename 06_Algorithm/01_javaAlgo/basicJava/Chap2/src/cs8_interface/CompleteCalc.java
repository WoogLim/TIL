package cs8_interface;

public class CompleteCalc extends Calculator{
	// 추상클래스를 상속받아 나머지 구현안된 메서드를 구현한다.
	// Calc를 상속받진 않지만 타입을 상속받는다. JAVA는 다중상속이 불가능하다.
	// 여러개의 인터페이스를 implements는 가능하다.
	@Override
	public int times(int num1, int num2) {
		return num1 * num2;
	}

	@Override
	public int divide(int num1, int num2) {
		if(num2 == 0)
			return ERROR;
		
		return num1 / num2;
	}
	
	public void showInfo() {
		System.out.println("모두 구현했습니다.");
	}
}
