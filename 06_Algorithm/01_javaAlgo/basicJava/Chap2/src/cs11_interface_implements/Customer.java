package cs11_interface_implements;

public class Customer implements Buy, Sell{

	@Override
	public void sell() {
		System.out.println("customer sell");
	}

	@Override
	public void buy() {
		System.out.println("customer buy");
	}

	// 두 인터페이스에서는 order라는 default 메서드를 사용한다.
	// 두 인터페이스를 모두 구현하므로 관계가 모호해지기 때문에 둘중 하나를 Override하여 재정의 하거나
	// 아예 재정의가 가능하다.

//	@Override
//	public void order() {
		// TODO Auto-generated method stub
//		Buy.super.order();
//	}

	@Override
	public void order() {
		System.out.println("customer order");
	}
	
	public void hello() {
		System.out.println("hello");
	}

}
