package cs2_TemplateMethod;

public class CarTest {
	
	public static void main(String[] args) {
		
		Car aiCar = new AICar();
		aiCar.run();
		
		System.out.println("================");
		
		Car mCar = new MenualCar();
		mCar.run();
		
	}

}
