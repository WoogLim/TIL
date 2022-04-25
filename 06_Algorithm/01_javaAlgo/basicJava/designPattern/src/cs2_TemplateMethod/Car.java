package cs2_TemplateMethod;

// 추상 클래스의 응용 - 템플릿 메서드 패턴
/** 템플릿 메서드
 * - 추상 메서드나 구현된 메서드를 활용해 코드의 흐름(시나리오)를 정의하는 메서드
 * - final로 선언하여 하위 클래스에서 재정의 할 수 없게 함
 * - 프레임워크에서 많의 사용되는 설계 패턴이며 추상클래스로 선언된 클래스에서 템플릿 메서드를 황용해
 * 흐름을 정의 하고 하위 클래스에서 다르게 구현되어야 하는 부분은 추상 메서드로 선언해 하위 클래스에서
 * 구현하도록 함
 */
public abstract class Car {
	
	public abstract void drive();
	public abstract void stop();
	
	// 기능을 추가할때
	public abstract void wiper();
	
	public void starCar() {
		System.out.println("시동을 켭니다.");
	}

	public void turnOff() {
		System.out.println("시동을 끕니다.");
	}
	
	// 구현된 메서드지만 명령문이 없다. > 필요한 경우에 재정의 할 경우 이용
	public void washCar() {}
	
	// 시나리오 하위 클래스에서 재정의하지 못하도록 final로 선언
	final public void run() {
		starCar();
		drive();
		wiper();
		stop();
		turnOff();
		// 추가 메서드 호출 
		washCar();
	}
}
