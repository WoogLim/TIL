// 클래스 문법이 추가되었지만 클래스 기반으로 동작하는 것이 아닌 프로토타입 기반으로 동작한다.

// 프로토타입 상속 예제 코드
var Human = function (type) {
  this.type = type || "human";
};

Human.isHuman = function (human) {
  return human instanceof Human;
};

Human.prototype.breathe = function () {
  alert("h-a-a-a-m");
};

var Zero = function (type, firstName, lastName) {
  Human.apply(this, arguments);
  this.firstName = firstName;
  this.lastName = lastName;
};

Zero.prototype = Object.create(Human.prototype);
Zero.prototype.constructor = Zero; // 상속하는 부분
Zero.prototype.sayName = function () {
  alert(this.firstName + " " + this.lastName);
};

var OldZero = new Zero("human", "Zero", "Cho");
console.log(Human.isHuman(OldZero)); // true;

// 클래스 기반 객체지향 같아서 보기 편하네

class Human {
  constructor(type = "human") {
    this.type = type;
  }

  static isHuman(human) {
    return human instanceof Human;
  }

  breathe() {
    alert("h-a-a-a-m");
  }
}

class Zero extends Human {
  constructor(type, firstName, lastName) {
    super(type);
    this.firstName = firstName;
    this.lastName = lastName;
  }

  sayName() {
    super.breathe();
    alert(`${this.firstName} ${this.lastName}`);
  }
}

const newZero = new Zero("human", "Zero", "Cho");
console.log(Human.isHuman(newZero)); // true;
