// 참조형 및 원시형 데이터 타입
const person = {
    name: 'Max'
};

const secondPerson = person; // person 주소의 포인터

const secondPerson2 = {
    ...person 
};

person.name = 'Manu'

// person 메모리에 저장되어있고 person에는 주소 포인터가 저장
// secondPerson에 person 포인터 저장
console.log(secondPerson); // Manu
console.log(secondPerson2); // 스프레드 연산자를 이용하면 해당 타입의 값을 가져온다.
                            // {} 클래스로 생성하여 다른 메모리 주소에 생성된다.
                            // Max
