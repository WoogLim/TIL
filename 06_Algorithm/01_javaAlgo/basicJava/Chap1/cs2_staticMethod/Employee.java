package cs2_staticMethod;

public class Employee {
	
	private static int serialNum = 1000;

	private int employeeId;
	private String employeeName;
	private String department;
	
	public Employee() {
		// ��� �ν��Ͻ����� serialNum �� �����ϸ鼭
		// ������ ������ �̸� ������ ��� �ѹ��� �����ش�.
		serialNum++;
		employeeId = serialNum;
	}
	
//	static �� ���α׷� ������ �޸𸮿� ���� �����ȴ�. ������ �ش� Ŭ������ �Ϲ� �޼��忡�� ���� ���ƹٴ�.
	public static int getSerialNum() {
//		1. �Լ� ���ο��� ����ϴ� ���������� ��� ����.
		int i = 0; 

//		2. static �޼��� �ȿ����� �ش� Ŭ������ ��������� ����� �� ����.
//		�ν��Ͻ� ������ ������ ������ ���� �����ϱ� ����.
//		employeeName = "Lee" (X)
		
		return serialNum;
	}

//	static ������ get�� �����ϵ��� �����Ѵ�.
//	public static void setSerialNum(int serialNum) {
//		Employee.serialNum = serialNum;
//	}

	public int getEmployeeId() {
//		�Ϲ� �޼��忡�� static ������ �����ϴ°��� ������ ���� �ʴ´�.
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
