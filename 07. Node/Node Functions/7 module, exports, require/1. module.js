// 2.modul의 var.js를 다음과 같이 수정해도 index.js에서는 동일한 방식으로 불러올 수 있다.
exports.odd = "홀수입니다.";
exports.even = "짝수입니다.";

// module.exports와 exports는 같은 객체를 참조한다.
// console.log(module.exports == exports) // true
// exports는 module.exports를 참조하고 module.exports는 {}객체를 참조한다.
// 단 exports는 객체만 사용가능하다. module.exports에 함수를 대입한 경우에는 exports 로 바꿀 수 없다. 참조관계를 깨면 안되기 때문에 한 모듈내 동시 사용하지 않아야한다.

// this
console.log(this);
console.log(this === module.exports);
console.log(this === exports);

function whatIsThis() {
    console.log('function', this === exports, this === global);
    // function false true
    // 최상위 스코프에 존재하는 this는 module.exports(또는 exports객체) 함수 선언문 내부의 this는 global 객체를 가리킨다.
}
whatIsThis();