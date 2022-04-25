package cs2_TemplateMethod;

public class MenualCar extends Car{

	@Override
	public void drive() {
		System.out.println("사람이 운전합니다.");
		System.out.println("사람이 핸들을 조작합니다.");
	}

	@Override
	public void stop() {
		System.out.println("장애물 앞에서 브레이크를 밟아서 멈춥니다.");
	}

	// 필요한 경우에 상위의 명령문이 없는 일반 메서드를 재정의한다.
	@Override
	public void wiper() {
		// TODO Auto-generated method stub
	}

}
