package cs5_polymophism;

public class VIPCustomer extends Customer{

	String agentID;
	double saleRatio;

	public VIPCustomer(int customerId, String customerName) {
		super(customerId, customerName);
		
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
	}
	
	// 메서드 재정의 우클릭 - Source - Override할 메서드 표시
	// @Override 어노테이션 - 컴파일러에게 Override된 메서드라고 알려줌
	
	@Override
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;
		price -= (int)(price * saleRatio);
		return price;
	}
	

	public String getAgentID() {
		return agentID;
	}
}
