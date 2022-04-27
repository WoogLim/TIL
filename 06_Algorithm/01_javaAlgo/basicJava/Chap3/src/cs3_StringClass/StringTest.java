package cs3_StringClass;

public class StringTest {
	
	public static void main(String[] args) {
		
		String java = new String("java");
		String android = new String("android");
		
		System.out.println(System.identityHashCode(java));
		// 연결된 java는 실제 주소값이 달라진다. 이런식으로 String을 연결하면 필요없는 메모리가 생성된다.
		// StringBuiler(멀티스레드), StringBuffer(단일스레드)를 활용해야한다.
		java = java.concat(android);
		
		System.out.println(System.identityHashCode(java));
		
	}

}
