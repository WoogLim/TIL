package cs11_interface_implements;

public class CustomerTest {
	
	public static void main(String[] args) {
		
	Customer customer = new Customer();
	customer.buy();
	customer.sell();
	customer.order();
	customer.hello();
	
	Buy buyer = customer;
	buyer.buy();
	buyer.order(); // 재정의된 메서드
	
	Sell seller = customer;
	seller.sell();
	seller.order(); // 재정의된 메서드
	
	}	

}
