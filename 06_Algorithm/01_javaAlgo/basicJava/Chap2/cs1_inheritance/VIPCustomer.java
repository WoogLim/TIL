package cs1_inheritance;

public class VIPCustomer extends Customer{
	
	String agentID; // 담당 agent
	double saleRatio; // 추가 할인
	
	public VIPCustomer() {
		
		bonusRatio = 0.05;
		saleRatio = 0.1;
//		상위클래스의 protected 접근 제어자로 멤버 변수 접근
		customerGrade = "VIP";
	}

	public String getAgentID() {
		return agentID;
	}
}
