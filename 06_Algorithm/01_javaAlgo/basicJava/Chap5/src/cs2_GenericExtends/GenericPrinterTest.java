package cs2_GenericExtends;

public class GenericPrinterTest {
	
	public static void main(String[] args) {
		
		Powder powder = new Powder();
		
		// Material를 상속한 Powder, Plastic만 가능
		GenericPrinter<Powder> powderPrinter = new GenericPrinter<>(); // Type: Powder 
		powderPrinter.setMaterial(powder);
	
		Powder p = powderPrinter.getMaterial();
		System.out.println(powderPrinter.toString());
		
		// public class GenericPrinter<T extends Material>
		// extends T 를 통해 사용 가능한 클래스를 한정할 수 있고 공통으로 사용될 메서드들을 구현할 수 있다. 
		// T는 Material 을 상속하므로 이를 상속한 Plastic, Powder Class만 타입으로 들어올 수 있다.
	}
}
