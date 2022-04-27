package cs2_Object2;

public class EqualsTest {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Student std1 = new Student(100, "Lee");
		Student std2 = new Student(100, "Lee");
		
		// 실제 주소는 다르지만 논리적으로는 같다. == 는 주소를 비교하므로 false
		System.out.println(std1 == std2);
		// equals로 두 객체 주소가 같은지 비교. 
//		System.out.println(std1.equals(std2)); > false 주소가 다르다.
		
		// Object부모의 equals를 오버라이딩하여 객체 타입, 논리적인 값이 같은 경우 true로 변경  
		System.out.println(std1.equals(std2)); 
		
		// Object부모의 hashcode를 오버라이딩하여 학번을 출력
		System.out.println(std1.hashCode());
		System.out.println(std2.hashCode());
		
		// 진짜 해쉬코드 확인
		System.out.println(System.identityHashCode(std1));
		System.out.println(System.identityHashCode(std2));
		
		String str1 = new String("abc");
		String str2 = new String("abc");
		
		// String 객체도 Object부모의 equals 를 오버라이딩 했다. 두 객체 타입과 값이 같다면 true이다.
		System.out.println(str1.equals(str2));
		
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		
		Integer i = 100;
		System.out.println(i.hashCode());
		
		// Clone
		std1.setStudentName("KIM");
		Student copyStudent = (Student)std1.clone();
		System.out.println(copyStudent);
	}
	
}
