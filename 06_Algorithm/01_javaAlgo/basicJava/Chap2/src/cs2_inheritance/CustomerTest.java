package cs2_inheritance;

public class CustomerTest {
	
	public static void main(String[] args) {
		
		Customer customerLee = new Customer(10010, "이순신");
//		customerLee.setCustomerName("이순신");
//		customerLee.setCustomerID(10010);
		customerLee.bonusPoint = 1000;
		System.out.println(customerLee.showCustomerInfo());
		
		VIPCustomer customerKim = new VIPCustomer(10020, "김유신");
//		customerKim.setCustomerName("김유신");
//		customerKim.setCustomerID(10020);
		customerKim.bonusPoint = 10000;
		System.out.println(customerKim.showCustomerInfo());
		
		// 자식의 private 변수(힙 메모리에 저장)는 사용할 수 없다.
		Customer vc = new VIPCustomer(12345, "noname");
		
		// 형 변환
		// 상위 클래스 변환이 된 경우 : 상위 타입 내 선언된 변수, 메서드만 사용 가능
		// 자식의 경우 상위 멤버변수등은 protected로 자식에서 접근이 가능하다.
		
		// 상위클래스의 메서드 재정의 Override
		
	}

}
