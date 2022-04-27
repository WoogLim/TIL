package cs3_StringClass;

public class StringBuilderTest {
	
	public static void main(String[] args) {
		String java = new String("java");
		String android = new String("android");
		
		// 같은 메모리를 바라본다.
		StringBuilder buffer = new StringBuilder(java);
		System.out.println(System.identityHashCode(java));
		buffer.append(android);
		System.out.println(System.identityHashCode(java));
		
		String test = buffer.toString();
		System.out.println(test);
	}

}
