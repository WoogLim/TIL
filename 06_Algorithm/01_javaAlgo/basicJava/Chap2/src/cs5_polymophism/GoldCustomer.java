package cs5_polymophism;

public class GoldCustomer extends Customer{

	double salesRatio;
	
	// default 생성자가 부모에 존재하지 않으므로 생성자 정의
	public GoldCustomer(int customerId, String customerName) {
		super(customerId, customerName);
	
		salesRatio = 0.1;
		bonusRatio = 0.02;
		customerGrade = "GOLD";
	}
	
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;
		return price - (int)(price * salesRatio);
	}

}
