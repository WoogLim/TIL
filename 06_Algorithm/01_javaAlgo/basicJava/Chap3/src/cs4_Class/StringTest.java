package cs4_Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class StringTest {

	public static void main(String[] args) throws ClassNotFoundException {
		
		// 동적 로딩 - 사용시 주의 철자가 틀리면 프로그램 에러 발생.
//		Class c = Class.forName("java.lang.string"); // 철자가 틀랴 에러 발생
		Class c = Class.forName("java.lang.String");
		
		// String Class 의 생성자들을 볼 수 있다.
		Constructor[] cons = c.getConstructors();
		for(Constructor co : cons) {
			System.out.println(co);
		}
		
		// String Class 의 메소드들을 볼 수 있다.
		Method[] m = c.getMethods();
		for(Method mth : m) {
			System.out.println(mth);
		}
		
		// 보통 사용하는 경우는 원격에 있는 클래스를 import할때 이용한다.
		
	}
	
}
