package cs3_GenericMethod;

public class GenericMethod {

	// <T, V> 두개의 매개변수 이용
	// 제너릭을 메소드로 사용하는경우 반환 자료형 앞에 사용할 제너릭 타입을 명시
	// 두개의 제너릭을 이용할 것임.
	public static <T, V> double makeRectangle(Point<T, V> p1, Point<T, V> p2) {

		double left = ((Number)p1.getX()).doubleValue();
		double right =((Number)p2.getX()).doubleValue();
		double top = ((Number)p1.getY()).doubleValue();
		double bottom = ((Number)p2.getY()).doubleValue();
		
		double width = right - left;
		double height = bottom - top;
		
		return width * height;
	}
	
	public static void main(String[] args) {
		
		Point<Integer, Double> p1 = new Point<Integer, Double>(0, 0.0);
		Point<Integer, Double> p2 = new Point<>(10, 10.0);
		
		double size = GenericMethod.<Integer, Double>makeRectangle(p1, p2);
		System.out.println(size);
	}
	
}
