package cs2_inheritance;

public class VIPCustomer extends Customer{

	String agentID;
	double saleRatio;
	
//	public VIPCustomer() {
//		super는 생략되어있다.
//		VIPCustomer 생성시 먼저 부모 클래스가 생성이 되어야하는데 super()는 컴파일러가 기본값으로 설정된다. 
//		super(); default로 코딩하지 않아도 자동 삽입
//		super(0, "no-name");
		
//		bonusRatio = 0.05;
//		saleRatio = 0.1;
//		customerGrade = "VIP";
		
//		System.out.println("VIPCustomer() call");
//	}

	public VIPCustomer(int customerId, String customerName) {
		super(customerId, customerName);
		
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
		
		System.out.println("VIPCustomer(int, String) call");
	}

	public String getAgentID() {
		return agentID;
	}
}
