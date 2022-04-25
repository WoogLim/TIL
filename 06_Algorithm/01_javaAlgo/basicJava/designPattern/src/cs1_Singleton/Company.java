package cs1_Singleton;

// 하나의 회사가 필요
public class Company {
	
//	유일한 인스턴스이로 static으로 선언
	private static Company instance = new Company();
//	아무나 생성할 수 없도록 private로 생성자 생성 굳이 내부 코딩은하지않음.
//	생성자가 없다면 default 생성자 호출하는데 이를 호출하지 못하도록 생성자 생성하는데 private로 생성
	private Company() {
		
	}
	
//	외부 접근을 허용 인스턴스를 반환한다.
//	일반메서드이면 인스턴스를 생성하고 호출할 수 있으므로 인스턴스 생성 필요없이 사용하도록 static으로 생성한다.
	public static Company getInstance() {
		// 만일 null 인 경우 생성
		if(instance == null) {
			instance = new Company();
		}
		return instance;
	}
	
}
