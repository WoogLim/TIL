package cs1_Singleton;

import java.util.Calendar;

public class CompanyTest {
	
	public static void main(String[] args) {
		
		// �Ѵ� ���� �ν��Ͻ��� ����
		Company company1 = Company.getInstance();
		Company company2 = Company.getInstance();
		
		System.out.println(company1);
		System.out.println(company2);
		
		// ��ǥ������ Calendar�� �̱��� ������ ����Ѵ�
		// ���̵��� ��� (-)�� ��� private (+)�� ��� public
		Calendar calendar = Calendar.getInstance();
	}

}
