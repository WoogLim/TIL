// 구조분해 할당시 객체와 배열으로부터 속성이나 요소를 꺼낼 수 있다.

// 객체의 속성을 같은 이름의 변수에 대입하는 코드

// var candyMachine = {
//   status: {
//     name: "node",
//     count: 5,
//   },
//   getCandy: function () {
//     this.status.count--;
//     return this.status.count;
//   },
// };
// var getCandy = candyMachine.getCandy;
// var count = candyMachine.status.count;

// console.log(getCandy);
// console.log(count);
// 아래 코드로 변환 가능하다.

const candyMachine = {
  status: {
    name: "node",
    count: 5,
  },
  getCandy() {
    this.status.count--;
    return this.status.count;
  },
};
const {
  getCandy,
  status: { count },
} = candyMachine;

// 이렇게 코딩하지말자.
console.log(candyMachine.getCandy);
console.log(candyMachine.status.count);

// 배열에 대한 구조분해 할당
var array = ["node.js", {}, 10, true];
var node = array[0];
var obj = array[1];
var bool = array[3];

// 다음과 같이 변환가능

const array = ["node.js", {}, 10, true];
const [node, obj, , bool] = array;
