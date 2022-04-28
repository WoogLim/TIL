package cs2_LinkedList;

public class MyLinkedList {

	private MyListNode head;
	int count;
	
	public MyLinkedList()
	{
		head = null;
		count = 0;
	}
	
	// 앞의 노드를 반드시 알아야한다. 앞의 노드는 내 링크를 알고 나는 다음 인덱스의 링크를 알아야한다.
	
	// 맨뒤에 적재 
	// 맨앞:head
	public MyListNode addElement( String data )
	{
		MyListNode newNode;
		
		// 들어온 데이터가 첫 노드인 경우
		if(head == null) {
			newNode = new MyListNode(data);
			head = newNode;
		}
		else { // 첫 번째 노드가 아닌경우. 맨마지막 노드인 경우는 뒤에 null을 가리킨다. 
			newNode = new MyListNode(data);
			MyListNode temp = head; // head(첫번째 노드)부터 탐색
			while(temp.next != null) { // temp.next가 null 인 동안. 그다음 노드가 없는 경우
				temp = temp.next;
			}
			temp.next = newNode; // 마지막 노드를 찾아와 해당 인덱스에 노드를 적재한다.
		}
		count++;
		
		return newNode;
	}
	
	public MyListNode insertElement(int position, String data )
	{
		int i;
		MyListNode tempNode = head; // 첫번째 노드
		MyListNode preNode = null; // 이전 노드
		
		MyListNode newNode = new MyListNode(data);
		
		if(position < 0 || position > count) {
			return null;
		}
		
		// 맨 앞인 경우
		if( position == 0) {
			newNode.next = head;
			head = newNode;
		}
		else { // 중간에 들어간 노드인 경우
			for(i = 0 ; i < position ; i++) {
				preNode = tempNode;
				tempNode = tempNode.next;
			}
			newNode.next = preNode.next;
			preNode.next = newNode;
		}
		
		count++;
		return newNode;
		
	}
	
	public MyListNode removeElement(int position)
	{
		int i;
		MyListNode tempNode = head;
		
		if(position >= count ){
			System.out.println("삭제 할 위치 오류입니다. 현재 리스트의 개수는 " + count +"개 입니다.");
			return null;
		}
		
		if(position == 0){  //맨 앞을 삭제하는
			head = tempNode.next;
		}
		else{
			MyListNode preNode = null;	
			for(i=0; i<position; i++){
				preNode = tempNode;
				tempNode = tempNode.next;
			}
			preNode.next = tempNode.next;
		}
		count--;
		System.out.println(position + "번째 항목 삭제되었습니다.");
		
		return tempNode;
	}
	
	public String getElement(int position)
	{
		int i;
		MyListNode tempNode = head;
		
		if(position >= count ){
			System.out.println("검색 위치 오류 입니다. 현재 리스트의 개수는 " + count +"개 입니다.");
			return new String("error");
		}
		
		if(position == 0){  //맨 인 경우

			return head.getData();
		}
		
		for(i=0; i<position; i++){
			tempNode = tempNode.next;
			
		}
		return tempNode.getData();
	}

	public MyListNode getNode(int position)
	{
		int i;
		MyListNode tempNode = head;
		
		if(position >= count ){
			System.out.println("검색 위치 오류 입니다. 현재 리스트의 개수는 " + count +"개 입니다.");
			return null;
		}
		
		if(position == 0){  //맨 인 경우

			return head;
		}
		
		for(i=0; i<position; i++){
			tempNode = tempNode.next;
			
		}
		return tempNode;
	}

	public void removeAll()
	{
		head = null;
		count = 0;
		
	}
	
	public int getSize()
	{
		return count;
	}
	
	public void printAll()
	{
		if(count == 0){
			System.out.println("출력할 내용이 없습니다.");
			return;
		}
		
		MyListNode temp = head;
		while(temp != null){
			System.out.print(temp.getData());
			temp = temp.next;
			if(temp!=null){
				System.out.print("->");
			}
		}
		System.out.println("");
	}
	
	public boolean isEmpty()
	{
		if(head == null) return true;
		else return false;
	}
	
}
