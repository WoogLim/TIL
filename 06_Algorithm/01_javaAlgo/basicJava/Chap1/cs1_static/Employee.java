package cs1_static;

public class Employee {
	
	public static int serialNum = 1000;

	private int employeeId;
	private String employeeName;
	private String department;
	
	public Employee() {
		// 모든 인스턴스에서 serialNum 을 참조하면서
		// 생성될 때마다 이를 참조해 사원 넘버를 정해준다.
		serialNum++;
		employeeId = serialNum;
	}
	
	public int getEmployeeId() {
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
