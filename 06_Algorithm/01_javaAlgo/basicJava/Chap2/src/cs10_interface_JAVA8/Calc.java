package cs10_interface_JAVA8;

public interface Calc {
	double PI = 3.14;
	int ERROR = -999999999;
	
	// 인터페이스내 모든 메서드는 추상 클래스로 생성된다.
	int add(int num1, int num2);
	int substract(int num1, int num2);
	int times(int num1, int num2);
	int divide(int num1, int num2);
	
	// JAVA8 이후 사용가능 - 인터페이스에서 기본으로 제공할 구현된 메서드
	default void description() {
		myMethod();
		System.out.println("정수의 사칙연산을 제공합니다.");
	}

	// JAVA8 이후 사용가능 - 정적 메서드
	static int total(int[] arr) {
		
		int total = 0;
		for(int num : arr) {
			total += num;
		}
		myStaticMethod();
		return total;
	}
	
	// private 메서드

	// JAVA9 이후 사용가능 - 정적 메서드
	private void myMethod() {
		System.out.println("myMethod");
	}

	// JAVA9 이후 사용가능 - 정적 private 메서드
	private static void myStaticMethod() {
		System.out.println("myStaticMethod");
	}
}
