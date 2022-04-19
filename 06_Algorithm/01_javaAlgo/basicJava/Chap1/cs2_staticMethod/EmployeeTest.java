package cs2_staticMethod;

public class EmployeeTest {

	public static void main(String[] args) {
		// public으로 static 정의를 해놓았기 때문에 접근이 가능하다.
		System.out.println(Employee.getSerialNum());

		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("이순신");

//		System.out.println(employeeLee.serialNum);
		System.out.println(employeeLee.getSerialNum());
		
		Employee employeeKim = new Employee();
		employeeKim.setEmployeeName("김유신");
		
//		데이터 영역, 스택 메모리, 힙 메모리에 대해 알것
//		지역변수(로컬변수)는 스택 메모리
//		- 함수 내부에서만 사용
//		- 함수 호출시 생성되고 함수가 끝나면 소멸
//		멤버변수(인스턴스 변수)는 힙 메모리
//		- 클래스 내부에서 사용. private가 아니면 참조 변수로 다른 클래스에서 사용가능 아니면 인스턴스화 해야함.
//		- 인스턴스 생성시 힙에 생성. 가비지 컬렉터가 메모리를 수거할 때 소멸
//		static변수(클래스 변수)는 데이터 영역
//		- 클래스 내부에서 사용 private가 아니면 클래스 이름으로 다른 클래스에서 사용 가능
//		- 프로그램 처음 시작시 상수와 함께 데이터 영역에 생성. 프로그램 끝나면 메로리를 해제할 때 소멸
		
//		멤버 변수가 너무 많으면 인스턴스 생성시 쓸데없는 메모리가 할당됨
//		static 변수는 그 메모리 영역을 차지하고 있으므로 너무 큰 메모리를 할당하는 것은 좋지 않음.
		System.out.println(employeeLee.getEmployeeName() + "님의 사번은" + employeeLee.getEmployeeId());
		System.out.println(employeeKim.getEmployeeName() + "님의 사번은" + employeeKim.getEmployeeId());
	}
	
}
