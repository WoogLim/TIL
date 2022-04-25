package cs11_interface_implements;

public interface Buy {
	
	void buy();

	default void order() {
		System.out.println("buy order");
	}
}
