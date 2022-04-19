package cs1_static;

public class EmployeeTest {

	public static void main(String[] args) {

		System.out.println(Employee.serialNum); // static은 메모리에 이미 적재되어있으므로 확인 가능
		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("이순신");

		System.out.println(employeeLee.serialNum);
		
		Employee employeeKim = new Employee();
		employeeKim.setEmployeeName("김유신");
		employeeKim.serialNum++;
		
		// 데이터 영역, 스택 메모리, 힙 메모리에 대해 알것
		// 힙 메모리(스택 메모리 참조) 영역에 employeeLee, employeeKim 인스턴스를 참조해 적재
		// (지역변수가 스택 메모리에 쌓임)스택 메모리 영역에 employeeLee, employeeKim 를 참조하고
		// 두개의 인스턴스는 데이터 영역에 하나의 static 시리얼을 참조하고있다. 프로그램이 언로드 되면 사라짐
		
		System.out.println(employeeLee.getEmployeeName() + "님의 사번은" + employeeLee.getEmployeeId());
		System.out.println(employeeKim.getEmployeeName() + "님의 사번은" + employeeKim.getEmployeeId());
	}
	
}
