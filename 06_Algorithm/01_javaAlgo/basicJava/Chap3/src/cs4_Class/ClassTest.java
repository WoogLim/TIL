package cs4_Class;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassTest {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Class c1 = Class.forName("cs4_Class.Person");
		
		// deplicated
		Person person = (Person)c1.newInstance();
		
		Class c2 = person.getClass();
		Person p = (Person)c2.newInstance();
		System.out.println(p);
		
		Class[] parameterTypes = {String.class};
		Constructor cons = c2.getConstructor(parameterTypes);
		
		Object[] initargs = {"Kim"};
		Person kimPerson = (Person)cons.newInstance(initargs);
		System.out.println(kimPerson);
		
		// local에 Person과 같이 해당 클래스가 없는 경우 사용
		// reflection 프로그래밍: class 클래스를 사용해 클래스의 정보등을 알고 인스턴스를 생성. 메서드를 호출하는 방식의 프로그래밍이다.
	}

}
