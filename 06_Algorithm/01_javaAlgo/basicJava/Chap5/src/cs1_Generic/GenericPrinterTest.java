package cs1_Generic;

public class GenericPrinterTest {
	
	public static void main(String[] args) {
		
		Powder powder = new Powder();
//		뒤에 <> 은 다이아몬드 연산자라고함. Powder는 생략됨. 컴파일러가 넣어준다.
//		GenericPrinter powderPrinter = new GenericPrinter<>(); // Type: Object
		GenericPrinter<Powder> powderPrinter = new GenericPrinter<>(); // Type: Powder 
		powderPrinter.setMaterial(powder);
	
		Powder p = powderPrinter.getMaterial();
		System.out.println(powderPrinter.toString());
		
		// 다이아몬드 연산자 <>
		// ArrayList list = new ArrayList<>(); // 다이아몬드 연산자 내부에서 자료형은 생략가능함.
		// 자바(10 After)
		// ArrayList list = new ArrayList() => var list = new ArrayList();
	}
}
