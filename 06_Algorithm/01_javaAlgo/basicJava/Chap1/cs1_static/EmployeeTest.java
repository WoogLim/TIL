package cs1_static;

public class EmployeeTest {

	public static void main(String[] args) {

		System.out.println(Employee.serialNum); // static�� �޸𸮿� �̹� ����Ǿ������Ƿ� Ȯ�� ����
		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("�̼���");

		System.out.println(employeeLee.serialNum);
		
		Employee employeeKim = new Employee();
		employeeKim.setEmployeeName("������");
		employeeKim.serialNum++;
		
		// ������ ����, ���� �޸�, �� �޸𸮿� ���� �˰�
		// �� �޸�(���� �޸� ����) ������ employeeLee, employeeKim �ν��Ͻ��� ������ ����
		// (���������� ���� �޸𸮿� ����)���� �޸� ������ employeeLee, employeeKim �� �����ϰ�
		// �ΰ��� �ν��Ͻ��� ������ ������ �ϳ��� static �ø����� �����ϰ��ִ�. ���α׷��� ��ε� �Ǹ� �����
		
		System.out.println(employeeLee.getEmployeeName() + "���� �����" + employeeLee.getEmployeeId());
		System.out.println(employeeKim.getEmployeeName() + "���� �����" + employeeKim.getEmployeeId());
	}
	
}
