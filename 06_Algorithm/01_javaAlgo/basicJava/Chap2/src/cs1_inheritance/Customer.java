package cs1_inheritance;

public class Customer {
//	하위 클래스 접근 불가. private
//	private int customerID;
//	private String customerName;
//	private String customerGrade;
//	protected: 하위 클래스 접근은 가능하지만 나머지는 private와 같이 외부 접근을 허용하지 않는다.
	protected int customerID;
	protected String customerName;
	protected String customerGrade;
	
	int bonusPoint;
	double bonusRatio;
	
	public Customer() {
		customerGrade = "SILBER";
		bonusRatio = 0.01;
	}
	
	public int calcPrice(int price) {
//		총액의 1%를 보너스 포인트 적립
//		아래처럼 등급별로 if문 분기가 많아진다면 상속을 고려해야함.
//		if( customerGrade == "SILVER")
//			bonusPoint += price * bonusRatio;
//		else if( customerGrade == "VIP") {}
		return price;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(String customerGrade) {
		this.customerGrade = customerGrade;
	}

	public String showCustomerInfo() {
		return customerName + "님의 등급은" + customerGrade + "이며, 보너스 포인트는" + bonusPoint + "입니다.";
	}
}
