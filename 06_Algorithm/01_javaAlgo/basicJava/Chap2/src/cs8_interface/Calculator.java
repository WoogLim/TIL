package cs8_interface;

public abstract class Calculator implements Calc{

	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	@Override
	public int substract(int num1, int num2) {
		return num1 - num2;
	}

//	위 2개만 구현할 것이므로 해당 클래스를 그냥 클래스가 아닌 추상클래스로 변경한다.
//	@Override
//	public int times(int num1, int num2) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int divide(int num1, int num2) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
