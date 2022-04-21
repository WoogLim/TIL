package cs3_inheritance;

public class CustomerTest {
	
	public static void main(String[] args) {
		
		Customer customerLee = new Customer(10010, "이순신");
		customerLee.bonusPoint = 10000;
		int price = customerLee.calcPrice(1000);
		System.out.println(customerLee.showCustomerInfo() + price);
		
		VIPCustomer customerKim = new VIPCustomer(10020, "김유신");
		customerKim.bonusPoint = 10000;
		price = customerKim.calcPrice(1000);
		System.out.println(customerKim.showCustomerInfo() + price);
		
		// Customer로 대입되어 Customer내 맴버만 보인다.
		// vc 변수 타입은 Customer이지만 인스턴스 타입은 VIPCustomer이다.
		// 자바에서는 항상 인스턴스의 메서드가 호출된다. 자바의 모든 메서드는 또한 가상 메서드임.
		Customer vc = new VIPCustomer(10030, "유신");
		vc.bonusPoint = 10000;
		// 재정의된 VIPCustomer의 calcPrice 적용
		price = vc.calcPrice(1000);
		System.out.println(vc.showCustomerInfo() + price);
		
		/** 프로그램 안의 A라는 함수가 있고 안에 변수들 ,명령들이 있을때
		 * 메모리에 올라가면 코드영역, 데이터 영역에 데이터가 쌓인다.
		 * 코드 영역에는 메서드, 데이터 영역에는 상수 및 static 변수
		 * 지역변수의 경우 스택
		 * 인스턴스 변수의 경우 힙
		 * 명령(메서드내 A+B등) 은 코드 영역에 저장
		 * A 호출시 함수 이름은 Address로 변환이 된다.
		 */
		
		/** 가상함수
		 *  https://gitlab.com/easyspubjava/javacoursework/-/tree/master/Chapter3/3-05
		 */
		
		
	}

}
