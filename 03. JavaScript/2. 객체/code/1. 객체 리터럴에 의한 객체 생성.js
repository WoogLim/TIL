var person = {
  name: "Lee",
  sayHello: function () {
    console.log(`Hello! My name is ${this.name}.`);
  },
};

console.log(typeof person); // object
console.log(person); // {name: 'Lee', sayHello: ƒ}

var person = {
  firstName: "Ung-mo", // 식별자 네이밍 규칙을 준수하는 프로퍼티 키
  "last-name": "Lee", // 식별자 네이밍 규칙을 준수하지 않는 프로퍼티 키
};

console.log(person);

var obj = {};
var key = "hello";

// ES5: 프로퍼티 키 동적 생성
obj[key] = "world";
// ES6: 계산된 프로퍼티 이름
var obj = { [key]: "world" };

console.log(obj);

var person = {
  name: 2,
};

// 마침표 표기법에 의한 프로퍼티 접근
console.log(person.name); // Lee

// 대괄호 표기법에 의한 프로퍼티 접근
console.log(person["name"]); // Lee

var person = {
  name: "Lee",
};

// person 객체에는 age 프로퍼티가 존재하지 않는다.
// age 프로퍼티가 동적으로 생성되고 값이 할당된다.
person.age = 20;

console.log(person);
