package cs8_interface;

public interface Calc {
	double PI = 3.14;
	int ERROR = -999999999;
	
	// 인터페이스내 모든 메서드는 추상 클래스로 생성된다.
	int add(int num1, int num2);
	int substract(int num1, int num2);
	int times(int num1, int num2);
	int divide(int num1, int num2);
	
}
