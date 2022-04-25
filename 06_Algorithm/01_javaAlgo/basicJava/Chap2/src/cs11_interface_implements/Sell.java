package cs11_interface_implements;

public interface Sell {

	void sell();
	
	default void order() {
		System.out.println("sell Order");
	}
}
