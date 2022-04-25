package cs1_Singleton;

import java.util.Calendar;

public class CompanyTest {
	
	public static void main(String[] args) {
		
		// 둘다 같은 인스턴스를 참조
		Company company1 = Company.getInstance();
		Company company2 = Company.getInstance();
		
		System.out.println(company1);
		System.out.println(company2);
		
		// 대표적으로 Calendar가 싱글톤 패턴을 사용한다
		// 가이드의 경우 (-)의 경우 private (+)의 경우 public
		Calendar calendar = Calendar.getInstance();
	}

}
