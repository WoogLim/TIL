const A = require('./globalA');

global.message = '안녕하세요'; // A는 global.message를 반환한다. global.변수명은 전역객체의 변수로 '안녕하세요'가 출력된다.
// node 터미널을 실행해 global을 입력하면 지원하는 노드의 전역 메소드를 확인할 수 있다.
console.log(A());