package cs1_Array;

public class MyArray {

	int[] intArr;   	//int array
	int count;  		//개수(용량)
		
	public int ARRAY_SIZE;
	public static final int ERROR_NUM = -999999999;
	
	// 기본 생성
	public MyArray()
	{
		count = 0;
		ARRAY_SIZE = 10;
		intArr = new int[ARRAY_SIZE];
	}
	
	public MyArray(int size)
	{
		count = 0;
		ARRAY_SIZE = size;
		intArr = new int[size];
	}
	
	// n번째(0~) 위치에 순서대로 적재
	public void addElement(int num)
	{
		if(count >= ARRAY_SIZE){
			System.out.println("not enough memory");
			return;
		}
		intArr[count++] = num;
				
	}

	public void insertElement(int position, int num)
	{
		// 넣으려는 인덱스 배열 위치 position, 넣을 값 num
		// 맨끝에 있는 인덱스를 한칸씩 밀고
		int i;
		
		// 0보다 작거나 카운트보다 큰 경우
		if(position < 0 || position > count) {
			
			return;
		}
		
		// 배열이 꽉차서 못넣는 경우
		if( count >= ARRAY_SIZE) {
			
			return;
		}
		
		// 마지막 인덱스 부터 뒤로 한칸씩 밀어 인덱스 공간 만들기 i는 값이 존재하는 마지막 인덱스. position 보단 크고 같은 인덱스
		for( i = count-1; i >= position ; i--){
			intArr[i+1]  = intArr[i];        // 하나씩 이동
		}
		
		intArr[position] = num;
		count++;
	}
	
	public int removeElement(int position)
	{
		// 특정 인덱스를 지우는 경우
		// position -> i i+1을 왼쪽으로 한칸씩 가져온다.
		
		int ret = ERROR_NUM;
		
		if(isEmpty()) {
			System.out.println("Array is empty");
			return ret;
		}
		
		// 배열 크기보다 작거나 큰 경우
		if(position < 0 || position > count - 1) {
			return ret;
		}
		
		ret = intArr[position];
		
		// 한 칸씩 가져오기
		for(int i = position ; i < count - 1 ; i++) {
			intArr[i] = intArr[i+1];
		}
		count --;
		return ret;
	}
	
	public int getSize()
	{
		return count;
	}
	
	public boolean isEmpty()
	{
		if(count == 0){
			return true;
		}
		else return false;
	}
	
	public int getElement(int position)
	{
		if(position < 0 || position > count-1){
			System.out.println("검색 위치 오류. 현재 리스트의 개수는 " + count +"개 입니다.");
			return ERROR_NUM;
		}
		return intArr[position];
	}
	
	public void printAll()
	{
		if(count == 0){
			System.out.println("출력할 내용이 없습니다.");
			return;
		}
			
		for(int i=0; i<count; i++){
			System.out.println(intArr[i]);
		}
		
	}
	
	public void removeAll()
	{
		for(int i=0; i<count; i++){
			intArr[i] = 0;
		}
		count = 0;
	}
}