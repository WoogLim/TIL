package cs1_Singleton;

// �ϳ��� ȸ�簡 �ʿ�
public class Company {
	
//	������ �ν��Ͻ��̷� static���� ����
	private static Company instance = new Company();
//	�ƹ��� ������ �� ������ private�� ������ ���� ���� ���� �ڵ�����������.
//	�����ڰ� ���ٸ� default ������ ȣ���ϴµ� �̸� ȣ������ ���ϵ��� ������ �����ϴµ� private�� ����
	private Company() {
		
	}
	
//	�ܺ� ������ ��� �ν��Ͻ��� ��ȯ�Ѵ�.
//	�Ϲݸ޼����̸� �ν��Ͻ��� �����ϰ� ȣ���� �� �����Ƿ� �ν��Ͻ� ���� �ʿ���� ����ϵ��� static���� �����Ѵ�.
	public static Company getInstance() {
		// ���� null �� ��� ����
		if(instance == null) {
			instance = new Company();
		}
		return instance;
	}
	
}
