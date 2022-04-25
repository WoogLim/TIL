package cs4_polymophism;

import java.util.ArrayList;

/** 다형성
 * 하위클래스에서 상속 후 메서드를 오버라이딩. 상위 클래스 타입으로 인스턴스화하여 상위 클래스로 형변환 
 * 어떠한 인스턴스가 들어갔는지에 따라 상속을하면 하위 클래스들을 하나의 상위 클래스 타입으로 핸들링이 가능하다.
 * 클래스간 결합도가 높아지기 때문에 상속에 관해선 신중히 설계해야함.(업 캐스팅)
 * 
 * 공통적으로 사용할 메서드는 상위 클래스에서 정의한다.
 * 
 * 다형성의 특징)
 * 다른 클래스들이 한꺼번에 동일한 타입인것처럼 쓰이지만 실제적인 작동은 각각 다르다
 * 상속과 메서드 재정의를 통해 확작성 있는 프로그램 구현 가능
 * 객체내 if문 분기가 많다면 상속 및 다형성을 고려해야함.
 * @author LIM
 *
 */

class Animal{
	public void move() {
		System.out.println("동물이 움직입니다.");
	}
}

class Human extends Animal{

	@Override
	public void move() {
		System.out.println("사람이 두 발로 걷습니다.");
	}
	
	public void readBook() {
		System.out.println("사람이 책을 읽습니다.");
	}
	
}

class Tiger extends Animal{

	@Override
	public void move() {
		System.out.println("호랑이가 네 발로 걷습니다.");
	}
	
	public void hunting() {
		System.out.println("호랑이가 사냥을 합니다.");
	}
	
}

class Eagle extends Animal{
	public void move() {
		System.out.println("독수리가 하늘을 날아 다닙니다.");
	}
	
	public void flying() {
		System.out.println("독수리가 양날개를 쭉 펴고 날아다닙니다.");
	}
}

public class AnimalTest {
	
	public static void main(String[] args) {
		
		Animal Animal = new Animal();
		// 각 클래스는 상위 클래스인 Animal로 형변환이 된다.
		Animal hAnimal = new Human();
		Animal tAnimal = new Tiger();
		Animal eAnimal = new Eagle();
	
		// 인스턴스화 하여 스택에 저장. moveAnimal 메서드를 호출하고 move주소로 메서드 호출. 명령문을 실행한다. 
//		AnimalTest test = new AnimalTest();
//		test.moveAnimal(Animal);
//		test.moveAnimal(hAnimal);
//		test.moveAnimal(tAnimal);
//		test.moveAnimal(eAnimal);
		
		ArrayList<Animal> animalList = new ArrayList<>();
		animalList.add(hAnimal);
		animalList.add(tAnimal);
		animalList.add(eAnimal);
		
		for(Animal amimal : animalList) {
			amimal.move();
		}
	}
	
	public void moveAnimal(Animal animal) {
		// 각 클래스마다 재정의된 move 명령이 호출된다. 
		animal.move(); // 다형성
	}

}
