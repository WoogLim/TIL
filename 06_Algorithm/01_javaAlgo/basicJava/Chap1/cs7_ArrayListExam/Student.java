package cs7_ArrayListExam;

import java.util.ArrayList;

public class Student {

	int studentId;
	String studentName;
	
	ArrayList<Subject> subjectList;
	
	Student(int student, String studentName){
		this.studentId = student;
		this.studentName = studentName;
		
		subjectList = new ArrayList<>();
	}
	
	public void addSubject(String name, int point) {
		Subject subject = new Subject();
		subject.setName(name);
		subject.setScorePoint(point);
		
		subjectList.add(subject);
	}
	
	public void showScoreInfo() {
		int total = 0;
		
		for(Subject subject : subjectList) {
			total += subject.getScorePoint();
			System.out.println(studentName + "�л��� ����" + subject.getName() + "������ ������" + subject.getScorePoint() + "�Դϴ�.");
		}
		System.out.println(studentName + "�л��� ������" + total + "�� �Դϴ�.");
	}
}
