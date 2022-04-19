package cs3_Array;

public class ArrayTest {
	public static void main(String[] args) {
		
		int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int total = 0;
		
//		기본 for
//		for(int i = 0, num = 1 ; i < arr.length ; i++) {
//			arr[i] = num++;
//		}
//		향상 for
		for( int num : arr) {
			total += num;
		}
		
		System.out.println(total);
	}
}
