package cs2_staticMethod;

public class EmployeeTest {

	public static void main(String[] args) {
		// public���� static ���Ǹ� �س��ұ� ������ ������ �����ϴ�.
		System.out.println(Employee.getSerialNum());

		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("�̼���");

//		System.out.println(employeeLee.serialNum);
		System.out.println(employeeLee.getSerialNum());
		
		Employee employeeKim = new Employee();
		employeeKim.setEmployeeName("������");
		
//		������ ����, ���� �޸�, �� �޸𸮿� ���� �˰�
//		��������(���ú���)�� ���� �޸�
//		- �Լ� ���ο����� ���
//		- �Լ� ȣ��� �����ǰ� �Լ��� ������ �Ҹ�
//		�������(�ν��Ͻ� ����)�� �� �޸�
//		- Ŭ���� ���ο��� ���. private�� �ƴϸ� ���� ������ �ٸ� Ŭ�������� ��밡�� �ƴϸ� �ν��Ͻ�ȭ �ؾ���.
//		- �ν��Ͻ� ������ ���� ����. ������ �÷��Ͱ� �޸𸮸� ������ �� �Ҹ�
//		static����(Ŭ���� ����)�� ������ ����
//		- Ŭ���� ���ο��� ��� private�� �ƴϸ� Ŭ���� �̸����� �ٸ� Ŭ�������� ��� ����
//		- ���α׷� ó�� ���۽� ����� �Բ� ������ ������ ����. ���α׷� ������ �޷θ��� ������ �� �Ҹ�
		
//		��� ������ �ʹ� ������ �ν��Ͻ� ������ �������� �޸𸮰� �Ҵ��
//		static ������ �� �޸� ������ �����ϰ� �����Ƿ� �ʹ� ū �޸𸮸� �Ҵ��ϴ� ���� ���� ����.
		System.out.println(employeeLee.getEmployeeName() + "���� �����" + employeeLee.getEmployeeId());
		System.out.println(employeeKim.getEmployeeName() + "���� �����" + employeeKim.getEmployeeId());
	}
	
}
