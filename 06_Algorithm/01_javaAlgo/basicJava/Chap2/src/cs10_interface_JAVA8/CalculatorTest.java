package cs10_interface_JAVA8;

public class CalculatorTest {
	
	public static void main(String[] args) {
		
		// 인터페이스의 정적 메서드 호출
		int[] arr = {1, 2, 3, 4, 5};
		System.out.println(Calc.total(arr));
		
		int num1 = 10;
		int num2 = 2;

		// 인터페이스를 구현한 클래스는 인터페이스 형으로 선언한 변수로 형 변환 가능
		// 타입은 Calc이고 사용할 수 있는 메서드는 Calc에서 정의한 메서드이다. 4개 모두 호출 가능
		// 만일 타입을 CompleteCalc 로 한다면 CompleteCalc에 구현한 메서드만 호출 가능
		Calc calc = new CompleteCalc();
		System.out.println(calc.add(num1, num2));
		System.out.println(calc.substract(num1, num2));
		System.out.println(calc.times(num1, num2));
		System.out.println(calc.divide(num1, num2));
		
		calc.description();
	}

}
