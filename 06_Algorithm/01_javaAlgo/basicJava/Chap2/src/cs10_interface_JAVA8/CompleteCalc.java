package cs10_interface_JAVA8;

public class CompleteCalc extends Calculator{
	
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

	// 인터페이스의 default 메서드 재정의가 가능하다.
//	@Override
//	public void description() {
//		super.description();
//		System.out.println("CompleteCalc overrding");
//	}
	
	
	
}
