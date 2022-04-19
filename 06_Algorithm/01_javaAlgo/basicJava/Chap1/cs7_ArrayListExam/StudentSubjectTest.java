package cs7_ArrayListExam;

public class StudentSubjectTest {
	
	public static void main(String[] args) {
		Student studentLee = new Student(1001, "Lee");
		
		studentLee.addSubject("����", 100);
		studentLee.addSubject("����", 50);
		
		Student studentKim = new Student(1002, "Kim");
		
		studentKim.addSubject("����", 70);
		studentKim.addSubject("����", 85);
		studentKim.addSubject("����", 100);
		
		studentLee.showScoreInfo();
		System.out.println("===================");
		studentKim.showScoreInfo();
	}
}
