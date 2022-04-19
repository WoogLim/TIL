package cs2_staticMethod;

public class Employee {
	
	private static int serialNum = 1000;

	private int employeeId;
	private String employeeName;
	private String department;
	
	public Employee() {
		// 모든 인스턴스에서 serialNum 을 참조하면서
		// 생성될 때마다 이를 참조해 사원 넘버를 정해준다.
		serialNum++;
		employeeId = serialNum;
	}
	
//	static 은 프로그램 생성시 메모리에 같이 생성된다. 때문에 해당 클래스내 일반 메서드에서 참조 간읗바다.
	public static int getSerialNum() {
//		1. 함수 내부에서 사용하는 지역변수는 사용 가능.
		int i = 0; 

//		2. static 메서드 안에서는 해당 클래스의 멤버변수를 사용할 수 없다.
//		인스턴스 변수는 생성된 시점에 참조 가능하기 때문.
//		employeeName = "Lee" (X)
		
		return serialNum;
	}

//	static 변수는 get만 가능하도록 설정한다.
//	public static void setSerialNum(int serialNum) {
//		Employee.serialNum = serialNum;
//	}

	public int getEmployeeId() {
//		일반 메서드에서 static 변수를 참조하는것은 문제가 되지 않는다.
//		serialNum = 1000;
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
}
