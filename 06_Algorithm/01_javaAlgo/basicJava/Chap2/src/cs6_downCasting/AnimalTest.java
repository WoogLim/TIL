package cs6_downCasting;

import java.util.ArrayList;

/** 다운 캐스팅
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
		AnimalTest test = new AnimalTest();
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
		
		test.testDownCasting(animalList);
	}	
	
//	public void moveAnimal(Animal animal) {
//		// 각 클래스마다 재정의된 move 명령이 호출된다. 
//		animal.move(); // 다형성
//	}
	
	// down casting 보다 다형성을 이용하는 편이 편리하다.
	// 반드시 원래 타입으로 되돌려 코드를 작성하는 경우 다운캐스팅을 이용한다.
	public void testDownCasting(ArrayList<Animal> list) {
		for(int i = 0 ; i < list.size() ; i ++) {
			
			Animal animal = list.get(i);
			
			if( animal instanceof Human) {
				Human human = (Human)animal;
				human.readBook();
			}
			else if( animal instanceof Tiger) {
				Tiger tiger = (Tiger)animal;
				tiger.hunting();
			}
			else if( animal instanceof Eagle) {
				Eagle eagle = (Eagle)animal;
				eagle.flying();
			}
			else {
				System.out.println("unsupported Type");
			}
		}
	}

}
