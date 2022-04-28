package cs1_Generic;

public class ThreeDPrinterTest {
	
	public static void main(String[] args) {
		
		Powder powder = new Powder();
		ThreeDPrinter3 printer = new ThreeDPrinter3();
		
		// Object로 형 변환됨.
		printer.setMaterial(powder);
		
		// 문제점 Object 타입이므로 p의 자료형으로 형변환하여 타입을 맞춰줘야하는 번거로움이 있다.
		Powder p = (Powder)printer.getMaterial();
	}

}
