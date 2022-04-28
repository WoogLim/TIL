package cs3_GenericMethod;

public class Point<T, V> {

	T x;
	V y;
	
	Point(T x,V y){
		this.x = x;
		this.y = y;
	}
	// x축 점
	public T getX() {
		return x;
	}
	// y축 점
	public V getY() {
		return y;
	}
	
}
