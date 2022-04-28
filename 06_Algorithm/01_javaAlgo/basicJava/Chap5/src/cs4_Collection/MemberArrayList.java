package cs4_Collection;

import java.util.ArrayList;

public class MemberArrayList {
	
	private ArrayList<Member> arrayList;
	
	public MemberArrayList() {
		arrayList = new ArrayList<>();
	}
	// arrayList 는 기본생성자 이용시 사이즈가 10으로 고정된다.
	// 아래처럼 사이즈를 정해주어 사이즈를 포함한 생성자를 생성할 수 있다.
	public MemberArrayList(int size) {
		arrayList = new ArrayList<>(size);
	}
	
	public void addMember(Member member) {
		arrayList.add(member);
	}
	
	public boolean removeMember(int memberId) {
		for(int i = 0 ; i < arrayList.size() ; i++) {
			Member member = arrayList.get(i);
			
			int tempId = member.getMemberId();
			
			if(tempId == memberId) {
				arrayList.remove(i);
				return true;
			}
		}
		System.out.println(memberId + "가 존재하지 않습니다.");
		return false;
	}
	
	public void showAllMember() {
		for(Member member : arrayList) {
			System.out.println(member);
		}
		System.out.println();
	}
	
}
