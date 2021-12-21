if (true) {
  var x = 3;
}
console.log(x);
// 3

if (true) {
  const y = 3;
}
console.log(y);
// Uncaught ReferenceError: y is not defined

/**
 * const, let과 var은 스코프 종류가 다르다.
 * var은 함수 스코프이지만 const, let은 블록 스코프를 가진다.
 * 호이스팅 문제 및 코드 관리가 수월해짐
 *
 * const의 경우 최초로 초기화 후 변경되지 않는 값
 * let은 변경되는 경우 사용한다.
 *
 */
