const dep1 = require('./dep1');
const dep2 = require('./dep2');

dep1();
dep2();

// dep1의 module.exports가 함수가 아니라 빈 객체로 표시된다.
// dep1이 참조되고 ddep2가 참조 되는데 순환 참조의 경우 순환 참조되는 대상을 빈 객체로 만든다.
