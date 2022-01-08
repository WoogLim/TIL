const { odd, even } = require('./var'); // 불러올 모듈의 경로를 적는다. 확장자명은 생략해도된다.
// require 함수로 var.js에 있던 값들을 불러온다. var.js파일의 module.exports에 담긴 객체를 불러와 사용한다.

function checkOddOrEven(num) {
    if(num % 2) { // 홀수면
        return odd;
    } // 정수 1
    return even;
}

module.exports = checkOddOrEven;

